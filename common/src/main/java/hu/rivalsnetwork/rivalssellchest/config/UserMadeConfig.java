package hu.rivalsnetwork.rivalssellchest.config;

import com.google.common.base.Preconditions;
import com.google.common.collect.HashBiMap;
import dev.dejvokep.boostedyaml.YamlDocument;
import hu.rivalsnetwork.rivalssellchest.RivalsSellChestPlugin;
import hu.rivalsnetwork.rivalssellchest.chests.AbstractChest;
import hu.rivalsnetwork.rivalssellchest.chests.ChestTicker;
import hu.rivalsnetwork.rivalssellchest.util.MessageUtil;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class UserMadeConfig extends AbstractConfig {
    private final List<YamlDocument> configs = new ArrayList<>();
    private static final HashBiMap<String, AbstractChest> chests = HashBiMap.create();
    private final InputStream defaults = RivalsSellChestPlugin.getInstance().getResource("chests/default.yml");

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

            try {
                configs.add(YamlDocument.create(file));
            } catch (IOException exception) {
                throw new RuntimeException(exception);
            }
        }
        loadChests();
    }

    private void loadChests() {
        for (YamlDocument document : configs) {
            AbstractChest chest = new AbstractChest()
                    .setBoost(document.getDouble("boost"))
                    .setSellInterval(document.getLong("sell-interval"))
                    .setHologramEnabled(document.getBoolean("hologram.enabled"))
                    .setHologramLines(document.getList("hologram.lines"))
                    .setHologramUpdateTicks(document.getLong("hologram.update"))
                    .setHologramHeight(document.getDouble("hologram.height"))
                    .setPersistentStats(document.getBoolean("persistent-stats"))
                    .setName(document.getString("name"))
                    .setFile(document);

            chests.put(document.getString("name"), chest);
            new ChestTicker().tick(chest);
        }
    }

    public static HashBiMap<String, AbstractChest> getChests() {
        return chests;
    }
}
