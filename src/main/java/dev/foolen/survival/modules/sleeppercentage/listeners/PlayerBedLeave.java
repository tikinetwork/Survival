package dev.foolen.survival.modules.sleeppercentage.listeners;

import dev.foolen.survival.SurvivalPlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedLeaveEvent;

public class PlayerBedLeave implements Listener {
    @EventHandler
    public void onPlayerBedLeaveEvent(PlayerBedLeaveEvent e) {
        Player p = e.getPlayer();
        Bukkit.broadcastMessage(SurvivalPlugin.PREFIX + ChatColor.GOLD + p.getName() + " got out of bed.");
    }
}
