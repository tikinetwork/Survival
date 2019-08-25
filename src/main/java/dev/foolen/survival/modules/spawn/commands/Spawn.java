package dev.foolen.survival.modules.spawn.commands;

import dev.foolen.survival.SurvivalPlugin;
import dev.foolen.survival.modules.spawn.SpawnModule;
import dev.foolen.survival.utils.Logger;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Spawn implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            Logger.info("This command can only be executed by a player!");
            return true;
        }

        Player p = (Player) sender;

        if (!p.hasPermission("survival.command.spawn")) {
            p.sendMessage(SurvivalPlugin.PREFIX + ChatColor.RED + "You do not have permission to execute this command!");
            return true;
        }

        Location spawnLocation = SpawnModule.getSpawnLocation();

        if (spawnLocation != null) {

            if (!p.hasPermission("survival.bypass.waiting")) {
                SpawnModule.addTeleportingPlayer(p.getUniqueId());
                p.sendMessage(SurvivalPlugin.PREFIX + ChatColor.GREEN + "Teleporting you in 10 seconds, please stand still.");

                Bukkit.getScheduler().scheduleSyncDelayedTask(SurvivalPlugin.getInstance(), new Runnable() {
                    @Override
                    public void run() {
                        if (SpawnModule.isTeleporting(p.getUniqueId())) {
                            SpawnModule.removeTeleportingPlayer(p.getUniqueId());

                            p.teleport(spawnLocation);
                        }
                    }
                }, 20 * 10); // 10 seconds
            } else {
                p.teleport(spawnLocation);
            }
        } else {
            p.sendMessage(SurvivalPlugin.PREFIX + ChatColor.RED + "Spawn location has not yet been set.");
        }
        return true;
    }
}
