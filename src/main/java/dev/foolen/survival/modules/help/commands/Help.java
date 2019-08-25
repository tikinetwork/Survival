package dev.foolen.survival.modules.help.commands;

import dev.foolen.survival.SurvivalPlugin;
import dev.foolen.survival.modules.help.HelpModule;
import dev.foolen.survival.modules.rules.RulesModule;
import dev.foolen.survival.utils.Logger;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.bukkit.plugin.PluginDescriptionFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Help implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            Logger.info("This command can only be executed by a player!");
            return true;
        }

        Player p = (Player) sender;

        if (!p.hasPermission("survival.command.help")) {
            p.sendMessage(SurvivalPlugin.PREFIX + ChatColor.RED + "You do not have permission to execute this command!");
            return true;
        }

        HashMap<String, HashMap<String, String>> playerCommands = HelpModule.getPlayerCommands(p);

        if (playerCommands.size() > 0) {
            p.sendMessage(SurvivalPlugin.PREFIX + "Commands:");
            playerCommands.forEach((cmd, data) -> {
                String usage = data.get("usage").replace("<command>", cmd);
                p.sendMessage(ChatColor.WHITE + " " + usage + ChatColor.GRAY + " - " + ChatColor.GREEN + data.get("description"));
            });
        } else {
            p.sendMessage(SurvivalPlugin.PREFIX + ChatColor.RED + "You do not have permission to any command.");
        }
        return true;
    }
}
