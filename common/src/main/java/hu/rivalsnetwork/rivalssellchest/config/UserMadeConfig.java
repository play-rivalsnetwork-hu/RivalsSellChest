package hu.rivalsnetwork.rivalssellchest.config;

import com.google.common.base.Preconditions;
import dev.dejvokep.boostedyaml.YamlDocument;
import hu.rivalsnetwork.rivalssellchest.RivalsSellChestPlugin;
import hu.rivalsnetwork.rivalssellchest.chests.AbstractChest;
import hu.rivalsnetwork.rivalssellchest.chests.ChestTicker;
import hu.rivalsnetwork.rivalssellchest.util.MessageUtil;
import org.bukkit.NamespacedKey;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;

public class UserMadeConfig extends AbstractConfig {
    private static final HashMap<String, AbstractChest> chests = new HashMap<>();
    private static final List<String> chestList = new ArrayList<>();
    private final List<YamlDocument> configs = new ArrayList<>();
    private final InputStream defaults = RivalsSellChestPlugin.getInstance().getResource("chests/default.yml");

    public static HashMap<String, AbstractChest> getChests() {
        return chests;
    }

    public static List<String> getChestList() {
        return chestList;
    }

    @Override
    public void initialize() {
        final File sellChestFolder = new File(RivalsSellChestPlugin.getInstance().getDataFolder(), "/chests");
        if (!sellChestFolder.exists()) {
            sellChestFolder.mkdir();
            try {
                Preconditions.checkNotNull(defaults, "Could not find defaults.yml in plugin's resources!");
                Files.copy(defaults, new File(sellChestFolder.toString(), "/default.yml").toPath());
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }

        File[] userMadeConfigs = sellChestFolder.listFiles();

        if (userMadeConfigs == null) {
            MessageUtil.log(Level.INFO, "No chests found!");
            return;
        }

        for (File file : userMadeConfigs) {
            if (!file.toString().contains(".yaml") && !file.toString().contains(".yml")) continue;

            configs.add(ConfigLoader.create(file));
        }
        loadChests();
    }

    private void loadChests() {
        for (YamlDocument document : configs) {
            NamespacedKey key = new NamespacedKey(RivalsSellChestPlugin.getInstance(), "sell_chest_" + document.getString("name").toLowerCase(Locale.ENGLISH));
            MessageUtil.debugMessage(document.getString("name"));

            AbstractChest chest = new AbstractChest()
                    .setBoost(document.getDouble("boost"))
                    .setSellInterval(document.getLong("sell-interval"))
                    .setHologramEnabled(document.getBoolean("hologram.enabled"))
                    .setHologramLines(document.getStringList("hologram.lines"))
                    .setHologramUpdateTicks(document.getLong("hologram.update"))
                    .setHologramHeight(document.getDouble("hologram.height"))
                    .setPersistentStats(document.getBoolean("persistent-stats"))
                    .setName(document.getString("name"))
                    .setKey(key)
                    .setFile(document);

            chest.createItemStack();

            chests.put(document.getString("name").toLowerCase(Locale.ENGLISH), chest);
            chestList.add(chest.name());
            new ChestTicker().tick(chest);
            MessageUtil.debugMessage(chest.toString());
        }
    }
}
