package com.klnsyfsun.battleroyale.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import static com.klnsyfsun.battleroyale.BattleRoyale.*;
import static java.nio.charset.StandardCharsets.UTF_8;

public class UpdateChecker {
    private static UpdateChecker updateChecker = new UpdateChecker();

    private UpdateChecker() {
    }

    public static UpdateChecker getInstance() {
        return updateChecker;
    }

    private String getLatestVersion() {
        try {
            URL url = new URL("https://raw.githubusercontent.com/Klnsyf-Sun/Battle-Royale/master/LATEST_VERSION");
            URLConnection urlConnection = url.openConnection();
            urlConnection.setConnectTimeout(5000);
            urlConnection.setDoOutput(true);
            InputStream inputStream = url.openStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, UTF_8));
            return bufferedReader.readLine();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void checkUpdate() {
        String currentVersion = plugin.getDescription().getVersion();
        String latestVersion = getLatestVersion();
        if (!currentVersion.equalsIgnoreCase(latestVersion)) {
            server.getConsoleSender().sendMessage(prefix + " §aNew Version Available: §b" + latestVersion);
            server.getConsoleSender().sendMessage(prefix + " §aLink: §b" + repository);
        }
    }
}
