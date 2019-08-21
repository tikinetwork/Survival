package dev.foolen.survival.modules.connectionmessages.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuit implements Listener {
    @EventHandler
    public void onPlayerQuitEvent(PlayerQuitEvent e) {
        e.setQuitMessage(null);
    }
}
