package dev.foolen.survival.modules.pvp.commands;

import dev.foolen.survival.SurvivalPlugin;
import dev.foolen.survival.modules.pvp.PVPModule;
import dev.foolen.survival.modules.rules.RulesModule;
import dev.foolen.survival.utils.Logger;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class PVP implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            Logger.info("This command can only be executed by a player!");
            return true;
        }

        Player p = (Player) sender;

        if (!p.hasPermission("survival.command.pvp")) {
            p.sendMessage(SurvivalPlugin.PREFIX + ChatColor.RED + "You do not have permission to execute this command!");
            return true;
        }

        p.sendMessage(SurvivalPlugin.PREFIX + ChatColor.GREEN + "Switching PVP mode, please stand still.");
        PVPModule.addTogglingPlayer(p);

        Bukkit.getScheduler().scheduleSyncDelayedTask(SurvivalPlugin.getInstance(), new Runnable() {
            @Override
            public void run() {
                if (PVPModule.isToggling(p)) {
                    if (PVPModule.togglePVP(p)) {
                        p.sendMessage(SurvivalPlugin.PREFIX + "PVP has been " + ChatColor.GRAY + "enabled" + ChatColor.GREEN + "!");
                    } else {
                        p.sendMessage(SurvivalPlugin.PREFIX + "PVP has been " + ChatColor.GRAY + "disabled" + ChatColor.GREEN + "!");
                    }

                    PVPModule.removeTogglingPlayer(p);
                }
            }
        }, 20 * 10); //Wait 10 seconds;
        return true;
    }
}
