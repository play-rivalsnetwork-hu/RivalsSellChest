package hu.rivalsnetwork.rivalssellchest.util;

import dev.dejvokep.boostedyaml.YamlDocument;
import hu.rivalsnetwork.rivalssellchest.RivalsSellChestPlugin;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.logging.Level;

public class MessageUtil {

    public void sendMessage(@NotNull Player player, @NotNull String message) {
        player.sendMessage(message);
    }

    public static void debugMessage(@NotNull String message) {
        RivalsSellChestPlugin.getInstance().getLogger().info(message);
    }

    public static void log(@NotNull Level level, @NotNull String message) {
        RivalsSellChestPlugin.getInstance().getLogger().log(level, message);
    }

    public static void sendOptionalMessage(@NotNull Player player, @NotNull YamlDocument document, @NotNull String route) {
        document.getOptionalString(route).ifPresent(player::sendMessage);
    }
}
