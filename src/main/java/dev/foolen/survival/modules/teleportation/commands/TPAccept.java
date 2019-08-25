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

public class TPAccept implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            Logger.info("This command can only be executed by a player!");
            return true;
        }

        Player p = (Player) sender;

        if (!p.hasPermission("survival.command.tpaccept") ||
                !p.hasPermission("survival.command.*") ||
                !p.hasPermission("survival.*")) {
            p.sendMessage(SurvivalPlugin.PREFIX + ChatColor.RED + "You do not have permission to execute this command!");
            return true;
        }

        if (args.length == 0) {
            p.sendMessage(SurvivalPlugin.PREFIX + ChatColor.RED + "Please specify a player.");
            p.sendMessage(SurvivalPlugin.PREFIX + ChatColor.RED + "Command usage: /tpaccept <name>");
            return true;
        }

        Player target = Bukkit.getPlayer(args[0]);
        TeleportationModule.respondToRequest(target, p);
        target.teleport(p.getLocation());
        p.sendMessage(SurvivalPlugin.PREFIX + target.getName() + "'s teleport request has been approved.");
        return true;
    }
}
