package dev.foolen.survival.modules.sleeppercentage.listeners;

import dev.foolen.survival.SurvivalPlugin;
import dev.foolen.survival.modules.sleeppercentage.SleepPercentageModule;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerBedEnterEvent.BedEnterResult;

import java.util.List;

public class PlayerBedEnter implements Listener {
    @EventHandler
    public void onPlayerBedEnterEvent(PlayerBedEnterEvent e) {
        if (e.getBedEnterResult() == BedEnterResult.OK) {
            Player p = e.getPlayer();

            SleepPercentageModule.addSleepingPlayer(p);
            Bukkit.broadcastMessage(SurvivalPlugin.PREFIX + ChatColor.GOLD + p.getName() + " went to bed.");

            if (SleepPercentageModule.getPercentageSleeping() >= SleepPercentageModule.getPercentageNeeded()) {
                World world = p.getWorld();
                world.setTime(1000);
                world.setWeatherDuration(0);

                SleepPercentageModule.getSleepingPlayers().forEach(player -> {
                    player.setHealth(20);
                    player.setFoodLevel(20);
                });
            } else {
                int totalPlayersSleeping = SleepPercentageModule.getSleepingPlayers().size();
                int totalPlayers = 0; // Only from normal world
                List<World> worlds = SurvivalPlugin.getInstance().getServer().getWorlds();
                for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                    if (onlinePlayer.getWorld().getEnvironment() == World.Environment.NORMAL) {
                        totalPlayers++;
                    }
                }
                int totalPlayersNeeded = ((int) Math.ceil((double) (totalPlayers / 100) * SleepPercentageModule.getPercentageNeeded()));
                int totalPlayersNeededToSkipNight = totalPlayersNeeded - totalPlayersSleeping;

                // 11 out of 50 players are sleeping. 5 more players needed to skip the night.
                Bukkit.broadcastMessage(SurvivalPlugin.PREFIX + ChatColor.GOLD +
                    totalPlayersSleeping + " out of " + totalPlayers + " are sleeping. " +
                    totalPlayersNeededToSkipNight + " more players needed to skip the night.");
            }
        }
    }
}
