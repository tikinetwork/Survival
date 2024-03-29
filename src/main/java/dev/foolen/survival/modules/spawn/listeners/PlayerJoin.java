package dev.foolen.survival.modules.spawn.listeners;

import dev.foolen.survival.SurvivalPlugin;
import dev.foolen.survival.modules.spawn.SpawnModule;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Location spawnLocation = SpawnModule.getSpawnLocation();

        Player p = e.getPlayer();

        if (spawnLocation != null) {
            if (!p.hasPlayedBefore()) {
                p.teleport(spawnLocation);
            }
        }

        if (SpawnModule.isTeleporting(p.getUniqueId())) {
            SpawnModule.removeTeleportingPlayer(p.getUniqueId());
            p.sendMessage(SurvivalPlugin.PREFIX + ChatColor.RED + "You moved! Teleportation cancelled.");
        }
    }
}
