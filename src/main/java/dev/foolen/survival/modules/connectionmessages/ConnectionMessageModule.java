package dev.foolen.survival.modules.connectionmessages;

import dev.foolen.survival.SurvivalPlugin;
import dev.foolen.survival.modules.connectionmessages.listeners.PlayerJoin;
import dev.foolen.survival.modules.connectionmessages.listeners.PlayerQuit;
import org.bukkit.plugin.PluginManager;

public class ConnectionMessageModule {
    public ConnectionMessageModule() {
        SurvivalPlugin plugin = SurvivalPlugin.getInstance();

        registerEvents(plugin);
    }

    private void registerEvents(SurvivalPlugin plugin) {
        PluginManager pm = plugin.getServer().getPluginManager();

        pm.registerEvents(new PlayerJoin(), plugin);
        pm.registerEvents(new PlayerQuit(), plugin);
    }
}
