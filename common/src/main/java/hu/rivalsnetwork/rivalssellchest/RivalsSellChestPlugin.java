package hu.rivalsnetwork.rivalssellchest;

import hu.rivalsnetwork.rivalssellchest.chests.ChestTicker;
import hu.rivalsnetwork.rivalssellchest.chests.PlacedChestLoader;
import hu.rivalsnetwork.rivalssellchest.config.ConfigLoader;
import hu.rivalsnetwork.rivalssellchest.listener.SellChestInteractListener;
import hu.rivalsnetwork.rivalssellchest.listener.UserConnectionListener;
import hu.rivalsnetwork.rivalssellchest.provider.ProviderLoader;
import hu.rivalsnetwork.rivalssellchest.util.MessageUtil;
import hu.rivalsnetwork.rivalssellchest.version.ServerVersionChecker;
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

        new ServerVersionChecker().verifyVersionSupport();
        new ConfigLoader().loadConfigs();
        PlacedChestLoader.loadAll();
        MessageUtil.update();
        new ProviderLoader().loadProviders();

        Bukkit.getPluginManager().registerEvents(new UserConnectionListener(), this);
        Bukkit.getPluginManager().registerEvents(new ChestTicker(), this);
        Bukkit.getPluginManager().registerEvents(new SellChestInteractListener(), this);
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
