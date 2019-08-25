package dev.foolen.survival.modules.teleportation.listeners;

import dev.foolen.survival.SurvivalPlugin;
import dev.foolen.survival.modules.teleportation.TeleportationModule;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuit implements Listener {
    @EventHandler
    public void PlayerMove(PlayerQuitEvent e) {
        Player p = e.getPlayer();

        if (TeleportationModule.isTeleporting(p)) {
            TeleportationModule.removeTeleportingPlayer(p);
        }
    }
}