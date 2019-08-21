package dev.foolen.survival.modules.rules.commands;

import dev.foolen.survival.SurvivalPlugin;
import dev.foolen.survival.modules.rules.RulesModule;
import dev.foolen.survival.modules.warp.WarpModule;
import dev.foolen.survival.utils.Logger;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;

public class Rules implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            Logger.info("This command can only be executed by a player!");
            return true;
        }

        Player p = (Player) sender;

        if (p.hasPermission("survival.command.rules") ||
                p.hasPermission("survival.command.*") ||
                p.hasPermission("survival.*")) {
            p.sendMessage(SurvivalPlugin.PREFIX + ChatColor.RED + "You do not have permission to execute this command!");
            return true;
        }

        ArrayList<String> rules = RulesModule.getRules();

        if (rules.size() > 0) {
            p.sendMessage(SurvivalPlugin.PREFIX + "Rules:");
            rules.forEach(rule -> {
                p.sendMessage(ChatColor.GRAY + "  - " + ChatColor.GREEN + rule);
            });
        } else {
            p.sendMessage(SurvivalPlugin.PREFIX + ChatColor.RED + "There are currently no rules available. WOOOO!!!");
        }
        return true;
    }
}
