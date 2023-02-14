package hu.rivalsnetwork.rivalssellchest.nms;

import org.bukkit.Location;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public interface NMSHandler {

    HashMap<ItemStack, Item> getEntities(Location loc);
}
