package dev.foolen.survival;

import dev.foolen.survival.modules.spawn.SpawnModule;
import dev.foolen.survival.modules.warp.WarpModule;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class SurvivalPlugin extends JavaPlugin {

    private static SurvivalPlugin instance;
    public static final String PREFIX = ChatColor.DARK_GRAY + "[" + ChatColor.GREEN + "Survival" + ChatColor.DARK_GRAY + "]" + ChatColor.GREEN;

    @Override
    public void onEnable() {
        instance = this;

        saveDefaultConfig();

        // Load modules
        new SpawnModule();
        new WarpModule();
    }

    public static SurvivalPlugin getInstance() {
        return instance;
    }
}
