package dev.foolen.survival.modules.warp.commands;

import dev.foolen.survival.SurvivalPlugin;
import dev.foolen.survival.utils.Logger;
import dev.foolen.survival.modules.warp.WarpModule;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;
import org.shanerx.configapi.ConfigFile;

public class SetWarp implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            Logger.info("This command can only be executed by a player!");
            return true;
        }

        Player p = (Player) sender;

        if (!p.hasPermission("survival.command.setwarp") ||
                !p.hasPermission("survival.command.*") ||
                !p.hasPermission("survival.*")) {
            p.sendMessage(SurvivalPlugin.PREFIX + ChatColor.RED + "You do not have permission to execute this command!");
            return true;
        }

        if (args.length == 0) {
            p.sendMessage(SurvivalPlugin.PREFIX + ChatColor.RED + "Please specify a name.");
            p.sendMessage(SurvivalPlugin.PREFIX + ChatColor.RED + "Command usage: /setwarp <name>");
            return true;
        }

        String warpName = args[0].toLowerCase();
        SurvivalPlugin plugin = SurvivalPlugin.getInstance();
        ConfigFile cf = new ConfigFile(plugin.getDataFolder(), "warps");
        cf.createConfig();
        Configuration config = cf.getConfig();
        Location loc = p.getLocation();

        config.set(warpName + ".x", loc.getX());
        config.set(warpName + ".y", loc.getY());
        config.set(warpName + ".z", loc.getZ());
        config.set(warpName + ".yaw", loc.getYaw());
        config.set(warpName + ".pitch", loc.getPitch());
        config.set(warpName + ".world", loc.getWorld().getName());

        cf.save();
        WarpModule.setWarpLocation(warpName, loc);

        p.sendMessage(SurvivalPlugin.PREFIX + ChatColor.GRAY + warpName + "'s " + ChatColor.GREEN + "warp location has been set!");
        return true;
    }
}
