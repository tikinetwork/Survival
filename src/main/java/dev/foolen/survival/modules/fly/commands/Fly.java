package dev.foolen.survival.modules.fly.commands;

import dev.foolen.survival.SurvivalPlugin;
import dev.foolen.survival.utils.Logger;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Fly implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            Logger.info("This command can only be executed by a player!");
            return true;
        }

        Player p = (Player) sender;

        if (p.hasPermission("survival.command.fly") ||
                p.hasPermission("survival.command.*") ||
                p.hasPermission("survival.*")) {
            p.sendMessage(SurvivalPlugin.PREFIX + ChatColor.RED + "You do not have permission to execute this command!");
            return true;
        }

        boolean fly = !p.getAllowFlight();
        p.setAllowFlight(fly);
        p.setFlying(fly);

        if (fly) {
            p.sendMessage(SurvivalPlugin.PREFIX + ChatColor.GREEN + "Fly mode has been enabled!");
        } else {
            p.sendMessage(SurvivalPlugin.PREFIX + ChatColor.RED + "Fly mode has been disabled!");
        }
        return true;
    }
}
