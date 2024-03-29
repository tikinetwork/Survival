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

        if (!p.hasPermission("survival.command.tpaccept")) {
            p.sendMessage(SurvivalPlugin.PREFIX + ChatColor.RED + "You do not have permission to execute this command!");
            return true;
        }

        if (args.length == 0) {
            p.sendMessage(SurvivalPlugin.PREFIX + ChatColor.RED + "Please specify a player.");
            p.sendMessage(SurvivalPlugin.PREFIX + ChatColor.RED + "Command usage: /tpaccept <name>");
            return true;
        }

        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            p.sendMessage(SurvivalPlugin.PREFIX + ChatColor.GRAY + args[0] + " could not be found! Is he/she online?");
        }

        TeleportationModule.respondToRequest(target, p);

        p.sendMessage(SurvivalPlugin.PREFIX + ChatColor.GRAY + target.getName() + "'s " + ChatColor.GREEN + "teleport request has been approved.");
        target.sendMessage(SurvivalPlugin.PREFIX + ChatColor.GRAY + p.getName() + ChatColor.GREEN + " approved your teleport request.");

        if (!target.hasPermission("survival.bypass.waiting")) {
            target.sendMessage(SurvivalPlugin.PREFIX + ChatColor.GREEN + "Teleporting you in 10 seconds, please stand still.");
            TeleportationModule.addTeleportingPlayer(target.getUniqueId());

            Bukkit.getScheduler().scheduleSyncDelayedTask(SurvivalPlugin.getInstance(), new Runnable() {
                @Override
                public void run() {
                    if (TeleportationModule.isTeleporting(target.getUniqueId())) {
                        TeleportationModule.removeTeleportingPlayer(target.getUniqueId());

                        target.teleport(p.getLocation());
                    }
                }
            }, 20 * 10); // wait 10 seconds
        } else {
            target.teleport(p.getLocation());
        }
        return true;
    }
}
