package dev.foolen.survival.modules.warp.commands;

import dev.foolen.survival.SurvivalPlugin;
import dev.foolen.survival.modules.utils.Logger;
import dev.foolen.survival.modules.warp.WarpModule;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;
import org.shanerx.configapi.ConfigFile;

public class DelWarp implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            Logger.info("This command can only be executed by a player!");
            return true;
        }

        Player p = (Player) sender;

        if (p.hasPermission("survival.command.delwarp") ||
                p.hasPermission("survival.command.*") ||
                p.hasPermission("survival.*")) {
            p.sendMessage(SurvivalPlugin.PREFIX + ChatColor.RED + "You do not have permission to execute this command!");
            return true;
        }

        if (args.length == 0) {
            p.sendMessage(SurvivalPlugin.PREFIX + ChatColor.RED + "Please specify a name.");
            p.sendMessage(SurvivalPlugin.PREFIX + ChatColor.RED + "Command usage: /delwarp <name>");
            return true;
        }

        String warpName = args[0].toLowerCase();
        SurvivalPlugin plugin = SurvivalPlugin.getInstance();
        ConfigFile cf = new ConfigFile(plugin.getDataFolder(), "warps");
        cf.createConfig();
        Configuration config = cf.getConfig();

        config.set(warpName, null);

        cf.save();
        WarpModule.removeWarpLocation(warpName);

        p.sendMessage(SurvivalPlugin.PREFIX + warpName + "'s warp location has been removed!");
        return true;
    }
}
