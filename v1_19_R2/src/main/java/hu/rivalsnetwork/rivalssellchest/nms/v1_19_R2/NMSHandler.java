package hu.rivalsnetwork.rivalssellchest.nms.v1_19_R2;

import hu.rivalsnetwork.rivalssellchest.RivalsSellChestPlugin;
import hu.rivalsnetwork.rivalssellchest.util.MessageUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_19_R2.CraftChunk;
import org.bukkit.craftbukkit.v1_19_R2.CraftServer;
import org.bukkit.craftbukkit.v1_19_R2.entity.CraftItem;
import org.bukkit.craftbukkit.v1_19_R2.inventory.CraftItemStack;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class NMSHandler implements hu.rivalsnetwork.rivalssellchest.nms.NMSHandler {

    @Override
    public HashMap<ItemStack, Item> getEntities(@NotNull Location loc) {
        long now = System.nanoTime();
        BlockPos blockPos = new BlockPos(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
        AABB pos = new AABB(blockPos.getX() >> 4 << 4, blockPos.getY() - 64, blockPos.getZ() >> 4 << 4, (blockPos.getX() >> 4 << 4) + 16, blockPos.getY() + 64, (blockPos.getZ() >> 4 << 4) + 16);
        Level level = ((CraftChunk) loc.getChunk()).getHandle().getLevel();

        MessageUtil.debugMessage(blockPos.toString());
        MessageUtil.debugMessage(pos.toString());

        MessageUtil.debugMessage(level.getEntitiesOfClass(ItemEntity.class, pos) + "");

        HashMap<ItemStack, Item> items = new HashMap<>();

        level.getEntitiesOfClass(ItemEntity.class, pos).forEach(itemEntity -> {
            MessageUtil.debugMessage(itemEntity.getType().toString());
            items.put(CraftItemStack.asCraftMirror(itemEntity.getItem()), new CraftItem(((CraftServer) Bukkit.getServer()).getServer().server, itemEntity));

            // Sync back to main thread as entities can't be removed off-main
            Bukkit.getScheduler().runTask(RivalsSellChestPlugin.getInstance(), itemEntity::discard);
        });

        MessageUtil.debugMessage("Took: " + (System.nanoTime() - now));
        return items;
    }
}
