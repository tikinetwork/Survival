package dev.foolen.survival.modules.teleportation.commands;

import dev.foolen.survival.SurvivalPlugin;
import dev.foolen.survival.modules.teleportation.TeleportationModule;
import dev.foolen.survival.utils.Logger;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TPA implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            Logger.info("This command can only be executed by a player!");
            return true;
        }

        Player p = (Player) sender;

        if (!p.hasPermission("survival.command.tpa")) {
            p.sendMessage(SurvivalPlugin.PREFIX + ChatColor.RED + "You do not have permission to execute this command!");
            return true;
        }

        if (args.length == 0) {
            p.sendMessage(SurvivalPlugin.PREFIX + ChatColor.RED + "Please specify a player.");
            p.sendMessage(SurvivalPlugin.PREFIX + ChatColor.RED + "Command usage: /tpa <name>");
            return true;
        }

        Player target = Bukkit.getPlayer(args[0]);

        if (target != null) {
            boolean requestSend = TeleportationModule.makeTeleportRequest(p, target);
            if (requestSend) {
                p.sendMessage(SurvivalPlugin.PREFIX + "Your teleportation request to " + ChatColor.GRAY + target.getName() + ChatColor.GREEN + " has been sent!");
                target.sendMessage(SurvivalPlugin.PREFIX + ChatColor.GOLD + p.getName() + " would like to teleport to you. Please respond with " + ChatColor.GRAY + "/tpaccept " + ChatColor.GOLD + "or " + ChatColor.GRAY + "/tpdeny" + ChatColor.GOLD + ".");
            } else {
                p.sendMessage(SurvivalPlugin.PREFIX + ChatColor.RED + "You already have an active teleport request to this player!");
            }
        } else {
            p.sendMessage(SurvivalPlugin.PREFIX + ChatColor.GRAY + args[0] + ChatColor.RED + " could not be found! Is he/she online?");
        }
        return true;
    }
}
