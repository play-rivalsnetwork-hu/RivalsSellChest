package hu.rivalsnetwork.rivalssellchest.user;

import hu.rivalsnetwork.rivalssellchest.RivalsSellChestPlugin;
import hu.rivalsnetwork.rivalssellchest.config.ConfigLoader;
import org.jetbrains.annotations.NotNull;

import java.io.File;

public class UserFileHandler {

    public static void createFile(@NotNull SellChestUser user) {
        final File file = new File(RivalsSellChestPlugin.getInstance().getDataFolder(), "/playerdata/" + user.uuid() + ".yml");
        user.setFile(ConfigLoader.create(file));
    }

    public static void createFile(@NotNull OfflineSellChestUser user) {
        final File file = new File(RivalsSellChestPlugin.getInstance().getDataFolder(), "/playerdata/" + user.uuid() + ".yml");
        user.setFile(ConfigLoader.create(file));
    }
}
