package dev.foolen.survival.modules.pvp.listeners;

import dev.foolen.survival.SurvivalPlugin;
import dev.foolen.survival.modules.pvp.PVPModule;
import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class EntityDamage implements Listener {
    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent e) {
        if (e.getDamager().getType() == EntityType.PLAYER) {
            Player damager = (Player) e.getDamager();

            if (e.getEntityType() == EntityType.PLAYER) {
                Player player = (Player) e.getEntity();

                if (PVPModule.hasPVPEnabled(damager)) {
                    if (!PVPModule.hasPVPEnabled(player)) {
                        e.setCancelled(true);
                    }
                } else {
                    e.setCancelled(true);
                }
            }
        }
    }
}
