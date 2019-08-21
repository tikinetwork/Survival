package dev.foolen.survival.modules.spawn.listeners;

import dev.foolen.survival.modules.spawn.SpawnModule;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Location spawnLocation = SpawnModule.getSpawnLocation();

        if (spawnLocation != null) {
            Player p = e.getPlayer();

            if (!p.hasPlayedBefore()) {
                p.teleport(spawnLocation);
            }
        }
    }
}
