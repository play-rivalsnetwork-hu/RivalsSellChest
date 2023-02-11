package hu.rivalsnetwork.rivalssellchest.listener;

import hu.rivalsnetwork.rivalssellchest.user.SellChestUser;
import hu.rivalsnetwork.rivalssellchest.user.UserFileHandler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.jetbrains.annotations.NotNull;

public class UserConnectionListener implements Listener {

    @EventHandler
    public void onPlayerJoinEvent(@NotNull PlayerJoinEvent event) {
        SellChestUser user = new SellChestUser(event.getPlayer().getUniqueId());
        UserFileHandler.createFile(user);

    }
}
