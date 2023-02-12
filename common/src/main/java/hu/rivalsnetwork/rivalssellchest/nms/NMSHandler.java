package hu.rivalsnetwork.rivalssellchest.nms;

import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public interface NMSHandler {

    List<ItemStack> getEntities(Location loc);
}
