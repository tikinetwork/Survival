package dev.foolen.survival.modules.home.commands;

import dev.foolen.survival.SurvivalPlugin;
import dev.foolen.survival.modules.home.HomeModule;
import dev.foolen.survival.utils.Logger;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;
import org.shanerx.configapi.ConfigFile;

import java.util.UUID;

public class SetHome implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            Logger.info("This command can only be executed by a player!");
            return true;
        }

        Player p = (Player) sender;

        if (!p.hasPermission("survival.command.sethome") ||
                !p.hasPermission("survival.command.*") ||
                !p.hasPermission("survival.*")) {
            p.sendMessage(SurvivalPlugin.PREFIX + ChatColor.RED + "You do not have permission to execute this command!");
            return true;
        }

        if (args.length == 0) {
            p.sendMessage(SurvivalPlugin.PREFIX + ChatColor.RED + "Please specify a name.");
            p.sendMessage(SurvivalPlugin.PREFIX + ChatColor.RED + "Command usage: /sethome <name>");
            return true;
        }

        String homeName = args[0].toLowerCase();
        SurvivalPlugin plugin = SurvivalPlugin.getInstance();

        ConfigFile cf = new ConfigFile(plugin.getDataFolder(), "homes");
        cf.createConfig();
        Configuration config = cf.getConfig();
        UUID uuid = p.getUniqueId();
        Location loc = p.getLocation();

        config.set(uuid + "." + homeName + ".x", loc.getX());
        config.set(uuid + "." + homeName + ".y", loc.getY());
        config.set(uuid + "." + homeName + ".z", loc.getZ());
        config.set(uuid + "." + homeName + ".yaw", loc.getYaw());
        config.set(uuid + "." + homeName + ".pitch", loc.getPitch());
        config.set(uuid + "." + homeName + ".world", loc.getWorld().getName());

        cf.save();
        HomeModule.setHome(uuid, homeName, loc);

        p.sendMessage(SurvivalPlugin.PREFIX + homeName + " has been set!");
        return true;
    }
}
