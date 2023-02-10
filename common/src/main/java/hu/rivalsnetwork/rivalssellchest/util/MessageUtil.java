package hu.rivalsnetwork.rivalssellchest.util;

import hu.rivalsnetwork.rivalssellchest.RivalsSellChestPlugin;
import org.bukkit.entity.Player;

public class MessageUtil {

    public void sendMessage(Player player, String message) {
        player.sendMessage(message);
    }

    public static void debugMessage(String message) {
        RivalsSellChestPlugin.getInstance().getLogger().info(message);
    }

    public static void logInfo(String message) {
        RivalsSellChestPlugin.getInstance().getLogger().info(message);
    }

    public static void logWarning(String message) {
        RivalsSellChestPlugin.getInstance().getLogger().warning(message);
    }
}
