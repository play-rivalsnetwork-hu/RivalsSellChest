package hu.rivalsnetwork.rivalssellchest.util;

import dev.dejvokep.boostedyaml.YamlDocument;
import hu.rivalsnetwork.rivalssellchest.RivalsSellChestPlugin;
import hu.rivalsnetwork.rivalssellchest.config.Config;
import hu.rivalsnetwork.rivalssellchest.config.Messages;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.logging.Level;

public class MessageUtil {
    private static YamlDocument messagesConfig;

    public static void update() {
        messagesConfig = Messages.getConfig();
    }

    public void sendMessage(@NotNull Player player, @NotNull String message) {
        player.sendMessage(message);
    }

    public static void debugMessage(@NotNull String message) {
        if (Config.getConfig().getBoolean("debug")) RivalsSellChestPlugin.getInstance().getLogger().info(message);
    }

    public static void log(@NotNull Level level, @NotNull String message) {
        RivalsSellChestPlugin.getInstance().getLogger().log(level, message);
    }

    public static void sendOptionalMessage(@NotNull Player player, @NotNull String route) {
        messagesConfig.getOptionalString(route).ifPresent(player::sendMessage);
    }
}
