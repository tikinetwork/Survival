package dev.foolen.survival.modules.pvp.listeners;

import dev.foolen.survival.modules.pvp.PVPModule;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuit implements Listener {
    @EventHandler
    public void PlayerQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();

        if (PVPModule.isToggling(p)) {
            PVPModule.removeTogglingPlayer(p);
        }
    }
}
