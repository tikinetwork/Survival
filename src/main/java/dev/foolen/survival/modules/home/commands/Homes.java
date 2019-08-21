package dev.foolen.survival.modules.home.commands;

import dev.foolen.survival.SurvivalPlugin;
import dev.foolen.survival.modules.home.HomeModule;
import dev.foolen.survival.utils.Logger;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class Homes implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            Logger.info("This command can only be executed by a player!");
            return true;
        }

        Player p = (Player) sender;

        if (p.hasPermission("survival.command.homes") ||
                p.hasPermission("survival.command.*") ||
                p.hasPermission("survival.*")) {
            p.sendMessage(SurvivalPlugin.PREFIX + ChatColor.RED + "You do not have permission to execute this command!");
            return true;
        }

        HashMap<String, Location> homes = HomeModule.getHomesFromPlayer(p.getUniqueId());

        if (homes.size() > 0) {
            StringBuilder homesStr = new StringBuilder();
            homes.forEach((name, location) -> {
                homesStr.append(ChatColor.GRAY).append(name).append(ChatColor.GREEN).append(", ");
            });
            homesStr.replace(homesStr.length() - 2, homesStr.length(), ""); //Remove last comma and space

            p.sendMessage(SurvivalPlugin.PREFIX + "Available homes: " + homesStr + ChatColor.GREEN + ".");
        } else {
            p.sendMessage(SurvivalPlugin.PREFIX + ChatColor.RED + "No homes have been set.");
        }
        return true;
    }
}
