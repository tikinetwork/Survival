package dev.foolen.survival.modules.home.commands;

import dev.foolen.survival.SurvivalPlugin;
import dev.foolen.survival.modules.home.HomeModule;
import dev.foolen.survival.utils.Logger;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Home implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            Logger.info("This command can only be executed by a player!");
            return true;
        }

        Player p = (Player) sender;

        if (!p.hasPermission("survival.command.home")) {
            p.sendMessage(SurvivalPlugin.PREFIX + ChatColor.RED + "You do not have permission to execute this command!");
            return true;
        }

        if (args.length == 0) {
            p.sendMessage(SurvivalPlugin.PREFIX + ChatColor.RED + "Please specify a name.");
            p.sendMessage(SurvivalPlugin.PREFIX + ChatColor.RED + "Command usage: /home <name>");
            return true;
        }

        String homeName = args[0].toLowerCase();

        if (HomeModule.getHomesFromPlayer(p.getUniqueId()) != null) {
            Location homeLocation = HomeModule.getHomeFromPlayer(p.getUniqueId(), homeName);

            if (homeLocation != null) {
                if (!p.hasPermission("survival.bypass.waiting")) {
                    HomeModule.addTeleportingPlayer(p.getUniqueId());
                    p.sendMessage(SurvivalPlugin.PREFIX + ChatColor.GREEN + "Teleporting you in 10 seconds, please stand still.");

                    Bukkit.getScheduler().scheduleSyncDelayedTask(SurvivalPlugin.getInstance(), new Runnable() {
                        @Override
                        public void run() {
                            if (HomeModule.isTeleporting(p.getUniqueId())) {
                                HomeModule.removeTeleportingPlayer(p.getUniqueId());

                                p.teleport(homeLocation);
                            }
                        }
                    }, 20 * 10); // 10 seconds
                } else {
                    p.teleport(homeLocation);
                }

                return true;
            }
        }

        p.sendMessage(SurvivalPlugin.PREFIX + ChatColor.GRAY + homeName + "'s" + ChatColor.RED + " location has not been set.");
        return true;
    }
}
