package dev.foolen.survival.modules.warp.listeners;

import dev.foolen.survival.modules.home.HomeModule;
import dev.foolen.survival.modules.warp.WarpModule;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuit implements Listener {
    @EventHandler
    public void PlayerMove(PlayerQuitEvent e) {
        Player p = e.getPlayer();

        if (WarpModule.isTeleporting(p.getUniqueId())) {
            WarpModule.removeTeleportingPlayer(p.getUniqueId());
        }
    }
}