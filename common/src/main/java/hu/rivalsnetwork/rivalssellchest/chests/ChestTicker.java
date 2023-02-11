package hu.rivalsnetwork.rivalssellchest.chests;

import hu.rivalsnetwork.rivalssellchest.RivalsSellChestPlugin;
import hu.rivalsnetwork.rivalssellchest.util.MessageUtil;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkLoadEvent;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class ChestTicker implements Listener {
    private final NamespacedKey chestChunkKey = new NamespacedKey(RivalsSellChestPlugin.getInstance(), "has_sell_chest");
    public static HashMap<Chunk, AbstractChest> chunkChestMap = new HashMap<>();

    public void tick(@NotNull AbstractChest chest) {

        Bukkit.getScheduler().runTaskTimer(RivalsSellChestPlugin.getInstance(), () -> chunkChestMap.forEach((chunk, abstractChest) -> {
            if (chunk == null) return;
            if (abstractChest == null) return;
            final long now = System.nanoTime();
            if (!chunk.isLoaded()) return;

            MessageUtil.debugMessage("Ticking with isLoaded: " + (System.nanoTime() - now));
        }),0L, chest.sellInterval());
    }

    @EventHandler
    public void onChunkLoad(ChunkLoadEvent event) {
        final long now = System.nanoTime();
        if (event.getChunk().getPersistentDataContainer().has(chestChunkKey)) {
            System.out.println("asd");
        }
        MessageUtil.debugMessage("ChunkLoad event checking if chunk is loaded: " + (System.nanoTime() - now));
    }
}
