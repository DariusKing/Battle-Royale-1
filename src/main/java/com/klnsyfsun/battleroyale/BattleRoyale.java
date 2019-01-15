package com.klnsyfsun.battleroyale;

import com.klnsyfsun.battleroyale.settings.Settings;
import com.klnsyfsun.battleroyale.utils.UpdateChecker;
import org.apache.commons.lang.SystemUtils;
import org.bukkit.Server;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class BattleRoyale extends JavaPlugin {
    public final static String prefix = "[§6Battle Royale§r]";
    public final static String repository = "https://github.com/Klnsyf-Sun/Battle-Royale";
    public static BattleRoyale plugin;
    public static Server server;
    public static File dataFolder;
    public static Settings settings;
    public static long startupTime;
    public static String nmsVersion;
    public final List<String> compatible = Arrays.asList("v1_9_R1", "v1_9_R2", "v1_10_R1", "v1_11_R1", "v1_12_R1");

    @Override
    public void onEnable() {
        startupTime = System.currentTimeMillis();
        plugin = this;

        server = getServer();
        dataFolder = getDataFolder();
        settings = new Settings();

        saveDefaultConfig();

        if (!SystemUtils.isJavaVersionAtLeast(1.8f))
            throw new IllegalStateException("[Battle Royale] Required Java Version is at least 1.8!");

        nmsVersion = server.getClass().getPackage().getName().replace("org.bukkit.craftbukkit.", "");
        if (!compatible.contains(nmsVersion)) {
            server.getPluginManager().disablePlugin(this);
            throw new IllegalStateException("[Battle Royale] Required Minecraft Server Version is at least 1.9 and lower than 1.13!");
        }

        if (Settings.doCheckUpdate) new BukkitRunnable() {
            @Override
            public void run() {
                UpdateChecker.getInstance().checkUpdate();
            }
        }.runTaskAsynchronously(this);

        startupTime = System.currentTimeMillis() - startupTime;
        server.getConsoleSender().sendMessage(prefix + " §aStartup Succeed in §b" + startupTime + "§a ms");
    }
}
