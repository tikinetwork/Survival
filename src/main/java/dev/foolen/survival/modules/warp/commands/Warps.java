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

import java.util.HashMap;

public class Warps implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            Logger.info("This command can only be executed by a player!");
            return true;
        }

        Player p = (Player) sender;

        if (p.hasPermission("survival.command.warps") ||
                p.hasPermission("survival.command.*") ||
                p.hasPermission("survival.*")) {
            p.sendMessage(SurvivalPlugin.PREFIX + ChatColor.RED + "You do not have permission to execute this command!");
            return true;
        }

        HashMap<String, Location> warpLocations = WarpModule.getWarpLocations();

        if (warpLocations.size() > 0) {
            StringBuilder warpsStr = new StringBuilder();
            warpLocations.forEach((name, location) -> {
                warpsStr.append(ChatColor.GRAY).append(name).append(ChatColor.GREEN).append(", ");
            });
            warpsStr.replace(warpsStr.length()-2, warpsStr.length(), ""); //Remove last comma and space

            p.sendMessage(SurvivalPlugin.PREFIX + "Available warps: " + warpsStr + ChatColor.GREEN + ".");
        } else {
            p.sendMessage(SurvivalPlugin.PREFIX + ChatColor.RED + "No warp locations have been set.");
        }
        return true;
    }
}
