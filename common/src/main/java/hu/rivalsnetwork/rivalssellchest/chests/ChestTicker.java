package hu.rivalsnetwork.rivalssellchest.chests;

import com.google.common.collect.HashBiMap;
import hu.rivalsnetwork.rivalssellchest.RivalsSellChestPlugin;
import hu.rivalsnetwork.rivalssellchest.nms.NMSSetup;
import hu.rivalsnetwork.rivalssellchest.util.MessageUtil;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;

public class ChestTicker implements Listener {
    private static final HashBiMap<Location, PlacedChest> chestsToTick = HashBiMap.create();

    public static HashBiMap<Location, PlacedChest> getChestsToTick() {
        return chestsToTick;
    }

    public void tick(@NotNull AbstractChest chest) {
        Bukkit.getScheduler().runTaskTimer(RivalsSellChestPlugin.getInstance(), () -> {
            for (PlacedChest placedChest : chestsToTick.values()) {
                if (!placedChest.location().isChunkLoaded()) return;

                placedChest.location().getWorld().getChunkAtAsync(placedChest.location()).thenAcceptAsync(chunk -> {
                    MessageUtil.debugMessage("Calling getEntities method");

                    NMSSetup.getHandler().getEntities(placedChest.location());
                });
            }
        }, 0L, chest.sellInterval());
    }
}
