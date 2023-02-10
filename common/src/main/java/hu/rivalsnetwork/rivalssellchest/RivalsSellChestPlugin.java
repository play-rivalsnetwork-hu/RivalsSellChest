package hu.rivalsnetwork.rivalssellchest;

import hu.rivalsnetwork.rivalssellchest.config.ConfigLoader;
import hu.rivalsnetwork.rivalssellchest.version.VersionChecker;
import org.bukkit.plugin.java.JavaPlugin;

public final class RivalsSellChestPlugin extends JavaPlugin {
    private static RivalsSellChestPlugin instance;

    public static synchronized RivalsSellChestPlugin getInstance() {
        if (instance == null) {
            instance = new RivalsSellChestPlugin();
        }
        return instance;
    }

    @Override
    public void onEnable() {
        new VersionChecker().verifyVersionSupport();
        new ConfigLoader().loadConfigs();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
