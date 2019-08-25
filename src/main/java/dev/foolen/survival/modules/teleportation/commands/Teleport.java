package dev.foolen.survival.modules.teleportation.commands;

import dev.foolen.survival.SurvivalPlugin;
import dev.foolen.survival.utils.Logger;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Teleport implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            Logger.info("This command can only be executed by a player!");
            return true;
        }

        Player p = (Player) sender;

        if (!p.hasPermission("survival.command.teleport")) {
            p.sendMessage(SurvivalPlugin.PREFIX + ChatColor.RED + "You do not have permission to execute this command!");
            return true;
        }

        if (args.length == 0) {
            p.sendMessage(SurvivalPlugin.PREFIX + ChatColor.RED + "Please specify a player.");
            p.sendMessage(SurvivalPlugin.PREFIX + ChatColor.RED + "Command usage: /teleport <name>");
            return true;
        }

        Player target = Bukkit.getPlayer(args[0]);

        if (target != null) {
            if (target.getName() == p.getName()) {
                p.sendMessage(SurvivalPlugin.PREFIX + ChatColor.RED + "You can't teleport to yourself!");
            } else {
                p.teleport(target.getLocation());
                p.sendMessage(SurvivalPlugin.PREFIX + "You have been teleport to " + ChatColor.GRAY + target.getName() + ChatColor.GREEN + "!");
            }
        } else {
            p.sendMessage(SurvivalPlugin.PREFIX + ChatColor.GRAY + args[0] + ChatColor.RED + " can not be found. Are you sure he is online on the same server as you?");
        }
        return true;
    }
}
