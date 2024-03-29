package dev.foolen.survival.modules.teleportation.commands;

import dev.foolen.survival.SurvivalPlugin;
import dev.foolen.survival.utils.Logger;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TPPos implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            Logger.info("This command can only be executed by a player!");
            return true;
        }

        Player p = (Player) sender;

        if (!p.hasPermission("survival.command.tppos")) {
            p.sendMessage(SurvivalPlugin.PREFIX + ChatColor.RED + "You do not have permission to execute this command!");
            return true;
        }

        if (args.length < 3) {
            p.sendMessage(SurvivalPlugin.PREFIX + ChatColor.RED + "Please specify a location.");
            p.sendMessage(SurvivalPlugin.PREFIX + ChatColor.RED + "Command usage: /tppos <x> <y> <z>");
            return true;
        }

        Location loc = p.getLocation();

        try {
            loc.setX(Double.parseDouble(args[0]));
            loc.setY(Double.parseDouble(args[1]));
            loc.setZ(Double.parseDouble(args[2]));

            p.teleport(loc);
            p.sendMessage(SurvivalPlugin.PREFIX + "You have been teleport!");
        } catch (NumberFormatException e) {
            p.sendMessage(SurvivalPlugin.PREFIX + ChatColor.RED + "Please specify a valid location.");
            p.sendMessage(SurvivalPlugin.PREFIX + ChatColor.RED + "Command usage: /tppos <x> <y> <z>");
        }
        return true;
    }
}
