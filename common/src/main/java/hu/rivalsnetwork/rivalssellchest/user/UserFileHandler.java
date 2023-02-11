package hu.rivalsnetwork.rivalssellchest.user;

import dev.dejvokep.boostedyaml.YamlDocument;
import hu.rivalsnetwork.rivalssellchest.RivalsSellChestPlugin;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;

public class UserFileHandler {

    public static void createFile(@NotNull SellChestUser user) {
        File file = new File(RivalsSellChestPlugin.getInstance().getDataFolder(), "/playerdata/" + user.uuid() + ".yml");
        try {
            user.setFile(YamlDocument.create(file));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
