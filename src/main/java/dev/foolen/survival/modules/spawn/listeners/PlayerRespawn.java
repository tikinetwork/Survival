package dev.foolen.survival.modules.spawn.listeners;

import dev.foolen.survival.modules.spawn.SpawnModule;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class PlayerRespawn implements Listener {
    @EventHandler
    public void PlayerMove(PlayerRespawnEvent e) {
        Player p = e.getPlayer();

        p.teleport(SpawnModule.getSpawnLocation());
    }
}