package dev.foolen.survival.modules.home.listeners;

import dev.foolen.survival.SurvivalPlugin;
import dev.foolen.survival.modules.home.HomeModule;
import dev.foolen.survival.modules.teleportation.TeleportationModule;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMove implements Listener {
    @EventHandler
    public void PlayerMove(PlayerMoveEvent e) {
        Player p = e.getPlayer();

        if (HomeModule.isTeleporting(p.getUniqueId())) {
            HomeModule.removeTeleportingPlayer(p.getUniqueId());
            p.sendMessage(SurvivalPlugin.PREFIX + ChatColor.RED + "You moved! Teleportation cancelled.");
        }
    }
}