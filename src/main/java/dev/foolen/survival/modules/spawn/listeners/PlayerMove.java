package dev.foolen.survival.modules.spawn.listeners;

import dev.foolen.survival.SurvivalPlugin;
import dev.foolen.survival.modules.spawn.SpawnModule;
import dev.foolen.survival.modules.warp.WarpModule;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMove implements Listener {
    @EventHandler
    public void PlayerMove(PlayerMoveEvent e) {
        Player p = e.getPlayer();

        if (SpawnModule.isTeleporting(p.getUniqueId())) {
            SpawnModule.removeTeleportingPlayer(p.getUniqueId());
            p.sendMessage(SurvivalPlugin.PREFIX + ChatColor.RED + "You moved! Teleportation cancelled.");
        }
    }
}