package dev.foolen.survival.modules.spawn.listeners;

import dev.foolen.survival.modules.spawn.SpawnModule;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Location spawnLocation = SpawnModule.getSpawnLocation();

        if (spawnLocation != null) {
            e.getPlayer().teleport(spawnLocation);
        }
    }
}
