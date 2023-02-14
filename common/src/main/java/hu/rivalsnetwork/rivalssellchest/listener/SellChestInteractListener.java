package hu.rivalsnetwork.rivalssellchest.listener;

import com.google.common.base.Preconditions;
import hu.rivalsnetwork.rivalssellchest.RivalsSellChestPlugin;
import hu.rivalsnetwork.rivalssellchest.chests.AbstractChest;
import hu.rivalsnetwork.rivalssellchest.chests.ChestTicker;
import hu.rivalsnetwork.rivalssellchest.chests.PlacedChest;
import hu.rivalsnetwork.rivalssellchest.config.UserMadeConfig;
import hu.rivalsnetwork.rivalssellchest.user.SellChestUser;
import hu.rivalsnetwork.rivalssellchest.user.Users;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

public class SellChestInteractListener implements Listener {
    private static final NamespacedKey itemsSold = new NamespacedKey(RivalsSellChestPlugin.getInstance(), "rivalssellchest_sold_items");
    private static final NamespacedKey money = new NamespacedKey(RivalsSellChestPlugin.getInstance(), "rivalssellchest_money");

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
                .setMoney(event.getItemInHand().getItemMeta().getPersistentDataContainer().getOrDefault(money, PersistentDataType.DOUBLE, 0D))
                .setItemsSold(event.getItemInHand().getItemMeta().getPersistentDataContainer().getOrDefault(itemsSold, PersistentDataType.LONG, 0L))
                .setOwnerName(event.getPlayer().getName())
                .setOwnerUUID(event.getPlayer().getUniqueId())
                .setLocation(event.getBlock().getLocation());

        user.placedChests().add(placedChest);
        ChestTicker.getChestsToTick().put(event.getBlock().getLocation(), placedChest);
        event.getBlock().setMetadata(placedChest.abstractChest().key().getKey(), new FixedMetadataValue(RivalsSellChestPlugin.getInstance(), 0));
    }

    @EventHandler
    public void onBlockBreakEvent(BlockBreakEvent event) {
        boolean isSellChest = false;

        for (String chests : UserMadeConfig.getChests().keySet()) {
            if (event.getBlock().hasMetadata("sell_chest_" + chests)) {
                isSellChest = true;
                break;
            }
        }

        if (!isSellChest) return;
        SellChestUser user = Users.getUser(event.getPlayer());
        Preconditions.checkNotNull(user, "The SellChestUser was null!");

        event.setCancelled(true);
        PlacedChest placedChest = ChestTicker.getChestsToTick().get(event.getBlock().getLocation());
        if (placedChest == null) return;

        AbstractChest abstractChest = placedChest.abstractChest();

        ItemStack item = abstractChest.itemStack();
        ItemMeta meta = item.getItemMeta();
        meta.getPersistentDataContainer().set(itemsSold, PersistentDataType.LONG, placedChest.itemsSold());
        meta.getPersistentDataContainer().set(money, PersistentDataType.DOUBLE, placedChest.money());
        item.setItemMeta(meta);

        event.getBlock().setType(Material.AIR);

        ChestTicker.getChestsToTick().remove(event.getBlock().getLocation());
        user.placedChests().remove(placedChest);
        event.getPlayer().getInventory().addItem(item);
    }
}
