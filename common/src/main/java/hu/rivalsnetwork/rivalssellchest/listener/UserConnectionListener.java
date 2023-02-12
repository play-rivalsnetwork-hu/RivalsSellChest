package hu.rivalsnetwork.rivalssellchest.listener;

import hu.rivalsnetwork.rivalssellchest.user.SellChestUser;
import hu.rivalsnetwork.rivalssellchest.user.Users;
import hu.rivalsnetwork.rivalssellchest.util.MessageUtil;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class UserConnectionListener implements Listener {

    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent event) {
        MessageUtil.debugMessage("PlayerJoinEvent");
        SellChestUser user = new SellChestUser(event.getPlayer().getUniqueId());
        user.load();
    }

    @EventHandler
    public void onPlayerQuitEvent(PlayerQuitEvent event) {
        SellChestUser user = Users.getUser(event.getPlayer().getUniqueId());
        if (user == null) return;
        user.unload();
    }
}
