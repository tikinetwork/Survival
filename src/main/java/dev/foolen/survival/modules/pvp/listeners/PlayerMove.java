package dev.foolen.survival.modules.pvp.listeners;

import dev.foolen.survival.SurvivalPlugin;
import dev.foolen.survival.modules.pvp.PVPModule;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMove implements Listener {
    @EventHandler
    public void PlayerMove(PlayerMoveEvent e) {
        Player p = e.getPlayer();

        if (PVPModule.isToggling(p)) {
            PVPModule.removeTogglingPlayer(p);
            p.sendMessage(SurvivalPlugin.PREFIX + ChatColor.RED + "You moved! PVP mode has not been changed.");
        }
    }
}
