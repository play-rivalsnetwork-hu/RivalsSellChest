package hu.rivalsnetwork.rivalssellchest.config;

import com.google.common.base.Preconditions;
import com.google.common.base.Verify;
import dev.dejvokep.boostedyaml.YamlDocument;
import hu.rivalsnetwork.rivalssellchest.RivalsSellChestPlugin;
import hu.rivalsnetwork.rivalssellchest.util.MessageUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class UserMadeConfig extends AbstractConfig {
    private static List<YamlDocument> configs = new ArrayList<>();

    @Override
    public void initialize() {
        final File sellChestFolder = new File(RivalsSellChestPlugin.getInstance().getDataFolder(), "/chests");
        if (!sellChestFolder.exists()) sellChestFolder.mkdir();

        File[] userMadeConfigs = sellChestFolder.listFiles();

        if (userMadeConfigs == null) {
            MessageUtil.log(Level.INFO, "No chests found!");
            return;
        }

        for (File file : userMadeConfigs) {
            if (!file.toString().contains(".yaml") && !file.toString().contains(".yml")) return;

            try {
                configs.add(YamlDocument.create(file));
            } catch (IOException exception) {
                throw new RuntimeException(exception);
            }
        }
    }
}
