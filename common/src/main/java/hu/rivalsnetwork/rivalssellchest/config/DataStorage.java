package hu.rivalsnetwork.rivalssellchest.config;

import dev.dejvokep.boostedyaml.YamlDocument;
import hu.rivalsnetwork.rivalssellchest.RivalsSellChestPlugin;

import java.io.File;
import java.io.IOException;

public class DataStorage extends AbstractConfig {
    private static YamlDocument config;

    public static YamlDocument getConfig() {
        return config;
    }

    @Override
    public void initialize() {
        if (!Config.getConfig().getString("storage.type").equalsIgnoreCase("local")) return;

        try {
            config = YamlDocument.create(new File(RivalsSellChestPlugin.getInstance().getDataFolder(), "data.yml"));
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
