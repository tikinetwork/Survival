package dev.foolen.survival.modules.help;

import dev.foolen.survival.SurvivalPlugin;
import dev.foolen.survival.modules.help.commands.Help;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;

import java.util.HashMap;

public class HelpModule {

    private static HashMap<String, HashMap<String, String>> commands;

    public HelpModule() {
        SurvivalPlugin plugin = SurvivalPlugin.getInstance();

        loadCommands();

        registerCommands(plugin);
    }

    private void registerCommands(SurvivalPlugin plugin) {
        plugin.getCommand("help").setExecutor(new Help());
    }

    private void loadCommands() {
        commands = new HashMap<>();

        PluginDescriptionFile pdf = SurvivalPlugin.getInstance().getDescription();

        pdf.getCommands().forEach((label, data) -> {
            HashMap<String, String> commandInfo = new HashMap<>();

            commandInfo.put("description", (String) data.get("description"));
            commandInfo.put("usage", (String) data.get("usage"));
            commandInfo.put("permission", "survival.command." + label);

            commands.put(label, commandInfo);
        });
    }

    public static HashMap<String, HashMap<String, String>> getPlayerCommands(Player p) {
        HashMap<String, HashMap<String, String>> playerCommands = new HashMap<>();

        commands.forEach((label, data) -> {
            if (p.hasPermission(data.get("permission"))) {
                playerCommands.put(label, data);
            }
        });

        return playerCommands;
    }
}
