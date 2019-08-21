package dev.foolen.survival.modules.spawn.commands;

import dev.foolen.survival.SurvivalPlugin;
import dev.foolen.survival.modules.spawn.SpawnModule;
import dev.foolen.survival.modules.utils.Logger;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;

public class SetSpawn implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            Logger.info("This command can only be executed by a player!");
            return true;
        }

        Player p = (Player) sender;

        if (p.hasPermission("survival.command.setspawn") ||
                p.hasPermission("survival.command.*") ||
                p.hasPermission("survival.*")) {
            p.sendMessage(SurvivalPlugin.PREFIX + ChatColor.RED + "You do not have permission to execute this command!");
            return true;
        }

        Configuration config = SurvivalPlugin.getInstance().getConfig();
        Location loc = p.getLocation();

        config.set("spawn.x", loc.getX());
        config.set("spawn.y", loc.getY());
        config.set("spawn.z", loc.getZ());
        config.set("spawn.yaw", loc.getYaw());
        config.set("spawn.pitch", loc.getPitch());
        config.set("spawn.world", loc.getWorld().getName());

        SurvivalPlugin.getInstance().saveConfig();
        SpawnModule.setSpawnLocation(loc);

        p.sendMessage(SurvivalPlugin.PREFIX + "Spawn location has been set!");
        return true;
    }
}
