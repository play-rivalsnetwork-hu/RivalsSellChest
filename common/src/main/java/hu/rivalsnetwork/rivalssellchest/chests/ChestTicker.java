package hu.rivalsnetwork.rivalssellchest.chests;

import hu.rivalsnetwork.rivalssellchest.RivalsSellChestPlugin;
import it.unimi.dsi.fastutil.Pair;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class ChestTicker implements Listener {
    public static HashMap<Chunk, Pair<AbstractChest, Player>> chunkChestMap = new HashMap<>();

    public void tick(@NotNull AbstractChest chest) {
        Bukkit.getScheduler().runTaskTimer(RivalsSellChestPlugin.getInstance(), () -> chunkChestMap.forEach((chunk, abstractChest) -> {
            if (chunk == null) return;
            if (abstractChest == null) return;
            if (!chunk.isLoaded()) return;


        }), 0L, chest.sellInterval());
    }
}
