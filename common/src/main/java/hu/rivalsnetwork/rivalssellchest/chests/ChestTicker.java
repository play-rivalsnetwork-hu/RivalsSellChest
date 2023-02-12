package hu.rivalsnetwork.rivalssellchest.chests;

import com.google.common.collect.HashBiMap;
import hu.rivalsnetwork.rivalssellchest.RivalsSellChestPlugin;
import hu.rivalsnetwork.rivalssellchest.nms.NMSSetup;
import hu.rivalsnetwork.rivalssellchest.provider.PricesProviderLoader;
import hu.rivalsnetwork.rivalssellchest.user.Users;
import hu.rivalsnetwork.rivalssellchest.util.MessageUtil;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ChestTicker implements Listener {
    private static final HashBiMap<Location, PlacedChest> chestsToTick = HashBiMap.create();

    public static HashBiMap<Location, PlacedChest> getChestsToTick() {
        return chestsToTick;
    }

    public void tick(@NotNull AbstractChest chest) {
        Bukkit.getScheduler().runTaskTimer(RivalsSellChestPlugin.getInstance(), () -> {
            for (PlacedChest placedChest : chestsToTick.values()) {
                if (placedChest.location().getWorld() == null) return;
                if (!placedChest.location().isChunkLoaded()) return;

                Bukkit.getScheduler().runTaskAsynchronously(RivalsSellChestPlugin.getInstance(), () -> {
                    List<ItemStack> items = NMSSetup.getHandler().getEntities(placedChest.location());

                    Bukkit.getScheduler().runTask(RivalsSellChestPlugin.getInstance(), () -> {
                        double amountToGive = 0;
                        for (ItemStack item : items) {
                            amountToGive = amountToGive + PricesProviderLoader.getProvider().getSellPrice(Users.getUser(placedChest), placedChest, item, item.getAmount());
                        }

                        MessageUtil.debugMessage("Giving " + amountToGive + " coins to " + placedChest.ownerName());
                    });
                });
            }
        }, 0L, chest.sellInterval());
    }
}
