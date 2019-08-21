package dev.foolen.survival.modules.enhancedmobdrops.listeners;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

public class EntityDeath implements Listener {
    @EventHandler
    public void EntityDeath(EntityDeathEvent e) {
        switch (e.getEntityType()) {
            case ENDER_DRAGON:
                e.getDrops().add(new ItemStack(Material.ELYTRA));
                break;
            case SHULKER:
                e.getDrops().add(new ItemStack(Material.SHULKER_SHELL, 2));
                break;
        }
    }
}
