package dev.foolen.survival.modules.enhancedmobdrops;

import dev.foolen.survival.SurvivalPlugin;
import dev.foolen.survival.modules.enhancedmobdrops.listeners.EntityDeath;
import org.bukkit.plugin.PluginManager;

public class EnhancedMobDropsModule {
    public EnhancedMobDropsModule() {
        SurvivalPlugin plugin = SurvivalPlugin.getInstance();

        registerEvents(plugin);
    }

    private void registerEvents(SurvivalPlugin plugin) {
        PluginManager pm = plugin.getServer().getPluginManager();

        pm.registerEvents(new EntityDeath(), plugin);
    }
}
