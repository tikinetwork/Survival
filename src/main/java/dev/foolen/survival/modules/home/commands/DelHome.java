package dev.foolen.survival.modules.home.commands;

import dev.foolen.survival.SurvivalPlugin;
import dev.foolen.survival.modules.home.HomeModule;
import dev.foolen.survival.utils.Logger;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;
import org.shanerx.configapi.ConfigFile;

import java.util.UUID;

public class DelHome implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            Logger.info("This command can only be executed by a player!");
            return true;
        }

        Player p = (Player) sender;

        if (!p.hasPermission("survival.command.delhome")) {
            p.sendMessage(SurvivalPlugin.PREFIX + ChatColor.RED + "You do not have permission to execute this command!");
            return true;
        }

        if (args.length == 0) {
            p.sendMessage(SurvivalPlugin.PREFIX + ChatColor.RED + "Please specify a name.");
            p.sendMessage(SurvivalPlugin.PREFIX + ChatColor.RED + "Command usage: /delhome <name>");
            return true;
        }

        SurvivalPlugin plugin = SurvivalPlugin.getInstance();
        ConfigFile cf = new ConfigFile(plugin.getDataFolder(), "homes");
        cf.createConfig();
        Configuration config = cf.getConfig();

        String homeName = args[0].toLowerCase();
        UUID uuid = p.getUniqueId();

        if (config.isSet(uuid.toString())) {
            if (config.getConfigurationSection(uuid.toString()).getKeys(false).size() > 1) {
                // removes only 1 home
                config.set(uuid + "." + homeName, null);

                HomeModule.removeHomeFromPlayer(uuid, homeName);
            } else {
                // removes entire player from config
                config.set(uuid.toString(), null);

                HomeModule.removePlayer(uuid);
            }

            cf.save();
            p.sendMessage(SurvivalPlugin.PREFIX + ChatColor.GRAY + homeName + "'s " + ChatColor.GREEN + "location has been removed.");
        } else {
            p.sendMessage(SurvivalPlugin.PREFIX + ChatColor.RED + "No homes have been set.");
        }
        return true;
    }
}
