package hu.rivalsnetwork.rivalssellchest;

import hu.rivalsnetwork.rivalssellchest.command.SellChestCommand;
import hu.rivalsnetwork.rivalssellchest.config.ConfigLoader;
import hu.rivalsnetwork.rivalssellchest.version.VersionChecker;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class RivalsSellChestPlugin extends JavaPlugin {
    private static RivalsSellChestPlugin instance;

    public static synchronized RivalsSellChestPlugin getInstance() {
        if (instance == null) {
            throw new RuntimeException("Plugin could not be initialized!");
        }
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;

        new VersionChecker().verifyVersionSupport();
        new ConfigLoader().loadConfigs();
        Bukkit.getPluginCommand("sellchest").setExecutor(new SellChestCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @Override
    public void onLoad() {
        super.onLoad();
    }
}
