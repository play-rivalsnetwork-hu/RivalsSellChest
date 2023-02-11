package hu.rivalsnetwork.rivalssellchest.nms.v1_19_R2;

import org.bukkit.Chunk;
import org.bukkit.craftbukkit.v1_19_R2.CraftChunk;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class NMSHandler implements hu.rivalsnetwork.rivalssellchest.nms.NMSHandler {

    @Override
    public List<Entity> getEntities(@NotNull Chunk chunk) {
        long now = System.currentTimeMillis();
        CraftChunk chunk2 = (CraftChunk) chunk;
        List<Entity> entities = Arrays.stream(chunk2.getEntities()).filter(entity -> entity.getType() == EntityType.DROPPED_ITEM).collect(Collectors.toList());
        System.out.println("Get Entities Method Run Took: " + (System.currentTimeMillis() - now) + "ms");
        return entities;
    }
}
