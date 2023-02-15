package hu.rivalsnetwork.rivalssellchest.user;

import dev.dejvokep.boostedyaml.YamlDocument;
import hu.rivalsnetwork.rivalssellchest.RivalsSellChestPlugin;
import hu.rivalsnetwork.rivalssellchest.config.ConfigLoader;
import hu.rivalsnetwork.rivalssellchest.util.MessageUtil;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.UUID;
import java.util.logging.Level;

public class OfflineUserLoader {

    public static void load(@NotNull YamlDocument config) {
        OfflineSellChestUser user = new OfflineSellChestUser(UUID.fromString(config.getString("uuid")));
        user.load();
    }

    public static void loadAll() {
        final File file = new File(RivalsSellChestPlugin.getInstance().getDataFolder(), "/playerdata");
        File[] files = file.listFiles();

        if (files == null) {
            MessageUtil.log(Level.INFO, "No playerdata files found!");
            return;
        }

        for (File playerDataFiles : files) {
            if (!playerDataFiles.toString().contains(".yaml") && !playerDataFiles.toString().contains(".yml")) continue;
            load(ConfigLoader.create(playerDataFiles));
        }
    }
}
