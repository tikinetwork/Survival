package dev.foolen.survival.modules.warp.commands;

import dev.foolen.survival.SurvivalPlugin;
import dev.foolen.survival.utils.Logger;
import dev.foolen.survival.modules.warp.WarpModule;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Warp implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            Logger.info("This command can only be executed by a player!");
            return true;
        }

        Player p = (Player) sender;

        if (p.hasPermission("survival.command.warp") ||
                p.hasPermission("survival.command.*") ||
                p.hasPermission("survival.*")) {
            p.sendMessage(SurvivalPlugin.PREFIX + ChatColor.RED + "You do not have permission to execute this command!");
            return true;
        }

        if (args.length == 0) {
            p.sendMessage(SurvivalPlugin.PREFIX + ChatColor.RED + "Please specify a name.");
            p.sendMessage(SurvivalPlugin.PREFIX + ChatColor.RED + "Command usage: /warp <name>");
            return true;
        }

        String warpName = args[0].toLowerCase();
        Location warpLocation = WarpModule.getWarpLocation(warpName);

        if (warpLocation != null) {
            p.teleport(warpLocation);
        } else {
            p.sendMessage(SurvivalPlugin.PREFIX + ChatColor.RED + warpName + "'s warp location has not been set.");
        }
        return true;
    }
}
