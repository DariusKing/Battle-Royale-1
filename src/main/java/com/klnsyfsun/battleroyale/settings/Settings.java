package com.klnsyfsun.battleroyale.settings;

import com.klnsyfsun.battleroyale.BattleRoyale;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class Settings {
    private static final String DEFAULT_LANGUAGE = "en_US";
    private static final boolean DEFAULT_DO_CHECK_UPDATE = false;
    public static String language;
    public static boolean doCheckUpdate;

    public Settings() {
        File configFile = new File(BattleRoyale.dataFolder, "config.yml");
        new YamlConfiguration();
        YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(configFile);
        language = yamlConfiguration.isString("language") ? yamlConfiguration.getString("language") : DEFAULT_LANGUAGE;
        doCheckUpdate = yamlConfiguration.isBoolean("enable-update-checker") ? yamlConfiguration.getBoolean("enable-update-checker") : DEFAULT_DO_CHECK_UPDATE;
    }
}
