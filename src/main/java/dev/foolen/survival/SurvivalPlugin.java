package dev.foolen.survival;

import dev.foolen.survival.modules.collision.CollisionModule;
import dev.foolen.survival.modules.connectionmessages.ConnectionMessageModule;
import dev.foolen.survival.modules.deathmessages.DeathMessageModule;
import dev.foolen.survival.modules.enhancedmobdrops.EnhancedMobDropsModule;
import dev.foolen.survival.modules.fly.FlyModule;
import dev.foolen.survival.modules.gamemode.GameModeModule;
import dev.foolen.survival.modules.help.HelpModule;
import dev.foolen.survival.modules.home.HomeModule;
import dev.foolen.survival.modules.pvp.PVPModule;
import dev.foolen.survival.modules.rules.RulesModule;
import dev.foolen.survival.modules.sleeppercentage.SleepPercentageModule;
import dev.foolen.survival.modules.spawn.SpawnModule;
import dev.foolen.survival.modules.teleportation.TeleportationModule;
import dev.foolen.survival.modules.warp.WarpModule;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class SurvivalPlugin extends JavaPlugin {

    private static SurvivalPlugin instance;
    public static final String PREFIX = ChatColor.DARK_GRAY + "[" + ChatColor.DARK_GREEN + "TikiNetwork" + ChatColor.DARK_GRAY + "]" + ChatColor.GREEN + " ";

    @Override
    public void onEnable() {
        instance = this;

        // Load default configuration options
        saveDefaultConfig();

        // Load modules
        new SpawnModule();
        new WarpModule();
        new HomeModule();
        new ConnectionMessageModule();
        new SleepPercentageModule();
        new EnhancedMobDropsModule();
        new RulesModule();
        new FlyModule();
        new GameModeModule();
        new TeleportationModule();
        new PVPModule();
        new CollisionModule();
        new HelpModule();
        new DeathMessageModule();
    }

    public static SurvivalPlugin getInstance() {
        return instance;
    }
}
