package hu.rivalsnetwork.rivalssellchest.util;

import org.bukkit.Material;
import org.bukkit.entity.Item;

import java.util.function.Predicate;

public class ChestUtil {

    public static final Predicate<Item> SUCTION_PREDICATE = (item) -> !item.isDead() && item.getItemStack().getType().equals(Material.AIR);

}
