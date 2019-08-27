package dev.foolen.survival.modules.deathmessages;

import dev.foolen.survival.SurvivalPlugin;
import dev.foolen.survival.modules.deathmessages.listeners.PlayerDeath;
import org.bukkit.plugin.PluginManager;

public class DeathMessageModule {
    public DeathMessageModule() {
        SurvivalPlugin plugin = SurvivalPlugin.getInstance();

        registerEvents(plugin);
    }

    private void registerEvents(SurvivalPlugin plugin) {
        PluginManager pm = plugin.getServer().getPluginManager();

        pm.registerEvents(new PlayerDeath(), plugin);
    }
}
