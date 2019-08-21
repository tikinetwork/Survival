package dev.foolen.survival.modules.warp.commands;

import dev.foolen.survival.SurvivalPlugin;
import dev.foolen.survival.modules.utils.Logger;
import dev.foolen.survival.modules.warp.WarpModule;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;

public class SetWarp implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            Logger.info("This command can only be executed by a player!");
            return true;
        }

        Player p = (Player) sender;

        if (p.hasPermission("survival.command.setwarp") ||
                p.hasPermission("survival.command.*") ||
                p.hasPermission("survival.*")) {
            p.sendMessage(SurvivalPlugin.PREFIX + ChatColor.RED + "You do not have permission to execute this command!");
            return true;
        }

        if (args.length == 0) {
            p.sendMessage(SurvivalPlugin.PREFIX + ChatColor.RED + "Please specify a name.");
            p.sendMessage(SurvivalPlugin.PREFIX + ChatColor.RED + "Command usage: /setwarp <name>");
            return true;
        }

        String warpName = args[0].toLowerCase();
        SurvivalPlugin plugin = SurvivalPlugin.getInstance();
        Configuration config = plugin.getConfig();
        Location loc = p.getLocation();

        config.set("warps." +warpName+ ".x", loc.getX());
        config.set("warps." +warpName+ ".y", loc.getY());
        config.set("warps." +warpName+ ".z", loc.getZ());
        config.set("warps." +warpName+ ".yaw", loc.getYaw());
        config.set("warps." +warpName+ ".pitch", loc.getPitch());
        config.set("warps." +warpName+ ".world", loc.getWorld().getName());

        plugin.saveConfig();
        WarpModule.setWarpLocation(warpName, loc);

        p.sendMessage(SurvivalPlugin.PREFIX + warpName + "'s warp location has been set!");
        return true;
    }
}
