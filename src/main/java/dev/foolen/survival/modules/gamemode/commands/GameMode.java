package dev.foolen.survival.modules.gamemode.commands;

import dev.foolen.survival.SurvivalPlugin;
import dev.foolen.survival.utils.Logger;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GameMode implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            Logger.info("This command can only be executed by a player!");
            return true;
        }

        Player p = (Player) sender;

        if (p.hasPermission("survival.command.gamemode") ||
                p.hasPermission("survival.command.*") ||
                p.hasPermission("survival.*")) {
            p.sendMessage(SurvivalPlugin.PREFIX + ChatColor.RED + "You do not have permission to execute this command!");
            return true;
        }

        if (args.length == 0) {
            p.sendMessage(SurvivalPlugin.PREFIX + ChatColor.RED + "Please specify a mode.");
            p.sendMessage(SurvivalPlugin.PREFIX + ChatColor.RED + "Command usage: /" + command.getLabel() + " <mode>");
            return true;
        }

        String mode = args[0].toUpperCase();

        switch(mode) {
            case "SURVIVAL":
            case "0":
                p.setGameMode(org.bukkit.GameMode.SURVIVAL);
                break;
            case "CREATIVE":
            case "1":
                p.setGameMode(org.bukkit.GameMode.CREATIVE);
                break;
            case "ADVENTURE":
            case "2":
                p.setGameMode(org.bukkit.GameMode.ADVENTURE);
                break;
            case "SPECTATOR":
            case "3":
                p.setGameMode(org.bukkit.GameMode.SPECTATOR);
                break;
        }

        String activeMode = p.getGameMode().toString().toLowerCase();
        p.sendMessage(SurvivalPlugin.PREFIX + "Your gamemode has been set to " + ChatColor.GRAY + activeMode + ChatColor.GREEN + "!");
        return true;
    }
}
