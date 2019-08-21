package dev.foolen.survival.modules.fly;

import dev.foolen.survival.SurvivalPlugin;
import dev.foolen.survival.modules.fly.commands.Fly;

public class FlyModule {
    public FlyModule() {
        SurvivalPlugin plugin = SurvivalPlugin.getInstance();

        registerCommands(plugin);
    }

    private void registerCommands(SurvivalPlugin plugin) {
        plugin.getCommand("fly").setExecutor(new Fly());
    }
}
