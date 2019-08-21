package dev.foolen.survival.modules.connectionmessages.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuit implements Listener {
    @EventHandler
    public void onPlayerQuitEvent(PlayerQuitEvent e) {
        Player p = e.getPlayer();

        if (p.hasPermission("group.vip") || p.hasPermission("group.vip+")) {
            e.setQuitMessage(ChatColor.GOLD + p.getName() + " left the game!");
        } else {
            e.setQuitMessage(null);
        }
    }
}
