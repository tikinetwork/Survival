package dev.foolen.survival.modules.collision;

import dev.foolen.survival.SurvivalPlugin;
import dev.foolen.survival.modules.collision.listeners.PlayerJoin;
import org.bukkit.plugin.PluginManager;

public class CollisionModule {
    public CollisionModule() {
        SurvivalPlugin plugin = SurvivalPlugin.getInstance();

        registerEvents(plugin);
    }

    private void registerEvents(SurvivalPlugin plugin) {
        PluginManager pm = plugin.getServer().getPluginManager();

        pm.registerEvents(new PlayerJoin(), plugin);
    }
}
