package dev.foolen.survival.modules.gamemode;

import dev.foolen.survival.SurvivalPlugin;
import dev.foolen.survival.modules.gamemode.commands.GameMode;

public class GameModeModule {
    public GameModeModule() {
        SurvivalPlugin plugin = SurvivalPlugin.getInstance();

        registerCommands(plugin);
    }

    private void registerCommands(SurvivalPlugin plugin) {
        plugin.getCommand("gm").setExecutor(new GameMode());
        plugin.getCommand("gamemode").setExecutor(new GameMode());
    }
}
