package dev.foolen.survival;

import dev.foolen.survival.modules.spawn.SpawnModule;
import org.bukkit.plugin.java.JavaPlugin;

public final class SurvivalPlugin extends JavaPlugin {

    private static SurvivalPlugin instance;
    public static final String PREFIX = "&8[&2Survival&8] &a";

    @Override
    public void onEnable() {
        instance = this;

        saveDefaultConfig();

        // Load modules
        new SpawnModule();
    }

    public static SurvivalPlugin getInstance() {
        return instance;
    }
}
