package hu.rivalsnetwork.rivalssellchest.chests;

import hu.rivalsnetwork.rivalssellchest.RivalsSellChestPlugin;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ChestTicker implements Listener {
    public static List<PlacedChest> chestList = new ArrayList<>();

    public void tick(@NotNull AbstractChest chest) {
        Bukkit.getScheduler().runTaskTimer(RivalsSellChestPlugin.getInstance(), () -> {
            for (PlacedChest placedChest : chestList) {
                placedChest.location().getWorld().getChunkAtAsync(placedChest.location()).thenAcceptAsync(chunk -> {
                    if (!chunk.isLoaded()) return;

                    System.out.println("Debug");
                });
            }
        }, 0L, chest.sellInterval());
    }
}
