package hu.rivalsnetwork.rivalssellchest.listener;

import hu.rivalsnetwork.rivalssellchest.chests.ChestTicker;
import hu.rivalsnetwork.rivalssellchest.chests.PlacedChest;
import hu.rivalsnetwork.rivalssellchest.config.UserMadeConfig;
import hu.rivalsnetwork.rivalssellchest.user.SellChestUser;
import hu.rivalsnetwork.rivalssellchest.user.Users;
import hu.rivalsnetwork.rivalssellchest.util.MessageUtil;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.jetbrains.annotations.NotNull;

public class SellChestInteractListener implements Listener {

    @EventHandler(ignoreCancelled = true)
    public void onBlockPlaceEvent(@NotNull BlockPlaceEvent event) {
        SellChestUser user = Users.getUser(event.getPlayer());
        if (user == null) return;

        boolean isSellChest = false;
        NamespacedKey chestKey = null;

        for (NamespacedKey key : event.getItemInHand().getItemMeta().getPersistentDataContainer().getKeys()) {
            if (key.getKey().contains("sell_chest")) {
                isSellChest = true;
                chestKey = key;
                break;
            }
        }

        if (!isSellChest) return;
        PlacedChest placedChest = new PlacedChest()
                .setAbstractChest(UserMadeConfig.getChests().get(chestKey.getKey().split("sell_chest_")[1]))
                .setAutoSellEnabled(true)
                .setChunkCollectEnabled(true)
                .setBank(false)
                .setMoney(0.0D)
                .setItemsSold(0L)
                .setOwnerName(event.getPlayer().getName())
                .setOwnerUUID(event.getPlayer().getUniqueId())
                .setLocation(event.getBlock().getLocation());

        user.placedChests().add(placedChest);
        ChestTicker.chestList.add(placedChest);
    }
}
