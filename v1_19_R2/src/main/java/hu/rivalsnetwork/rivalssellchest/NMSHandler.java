package hu.rivalsnetwork.rivalssellchest;

import net.minecraft.world.entity.item.ItemEntity;
import org.bukkit.Chunk;
import org.bukkit.craftbukkit.v1_19_R2.CraftChunk;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class NMSHandler implements hu.rivalsnetwork.rivalssellchest.nms.NMSHandler {

    @Override
    public List<Entity> getEntities(@NotNull Chunk chunk) {
        long now = System.currentTimeMillis();
        CraftChunk chunk2 = (CraftChunk) chunk;
        List<Entity> entities = Arrays.stream(chunk2.getEntities()).filter(entity -> entity instanceof ItemEntity).collect(Collectors.toList());
        System.out.println("Get Entities Method Run Took: " + (System.currentTimeMillis() - now) + "ms");
        return entities;
    }
}
