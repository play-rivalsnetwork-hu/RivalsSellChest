package hu.rivalsnetwork.rivalssellchest.chests;

import com.google.common.collect.HashBiMap;
import hu.rivalsnetwork.rivalssellchest.RivalsSellChestPlugin;
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
        Bukkit.getScheduler().runTaskTimerAsynchronously(RivalsSellChestPlugin.getInstance(), () -> {
            for (PlacedChest placedChest : chestsToTick.values()) {
                if (placedChest.location().getWorld() == null) return;
                MessageUtil.debugMessage("Chunk x: " + (placedChest.location().getBlockX() >> 4) + " z: " + (placedChest.location().getBlockZ() >> 4));
                if (!placedChest.location().getWorld().isChunkLoaded(placedChest.location().getBlockX() >> 4, placedChest.location().getBlockZ() >> 4)) {

                    MessageUtil.debugMessage("Chunk not loaded!");
                    continue;
                }

                try {
                    Thread.sleep(10);
                } catch (InterruptedException exception) {
                    exception.printStackTrace();
                }
                placedChest.tick();
            }
        }, 0L, chest.sellInterval());
    }
}
