package dev.foolen.survival.modules.rules;

import dev.foolen.survival.SurvivalPlugin;
import dev.foolen.survival.modules.rules.commands.Rules;
import org.bukkit.configuration.Configuration;

import java.util.ArrayList;

public class RulesModule {

    private static ArrayList<String> rules;

    public RulesModule() {
        SurvivalPlugin plugin = SurvivalPlugin.getInstance();

        loadRules(plugin);
        registerCommands(plugin);
    }

    private void loadRules(SurvivalPlugin plugin) {
        rules = new ArrayList<>();
        Configuration config = plugin.getConfig();
        if (config.isSet("rules")) {
            rules.addAll(config.getStringList("rules"));
        }
    }

    private void registerCommands(SurvivalPlugin plugin) {
        plugin.getCommand("rules").setExecutor(new Rules());
    }

    public static ArrayList<String> getRules() {
        return rules;
    }
}
