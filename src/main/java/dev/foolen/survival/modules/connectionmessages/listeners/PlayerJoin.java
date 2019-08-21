package dev.foolen.survival.modules.connectionmessages.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {
    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent e) {
        Player p = e.getPlayer();

        if (p.hasPermission("group.vip") || p.hasPermission("group.vip+")) {
            e.setJoinMessage(ChatColor.GOLD + p.getName() + " joined the game!");
        } else {
            e.setJoinMessage(null);
        }
    }
}
