package hu.rivalsnetwork.rivalssellchest.provider.prices;

import hu.rivalsnetwork.rivalssellchest.chests.PlacedChest;
import hu.rivalsnetwork.rivalssellchest.user.SellChestUser;
import org.bukkit.inventory.ItemStack;

public interface PricesProvider {

    double getSellPrice(ItemStack itemStack);

    double getSellPrice(ItemStack itemStack, int amount);

    double getSellPrice(SellChestUser user, ItemStack itemStack);

    double getSellPrice(SellChestUser user, ItemStack itemStack, int amount);

    double getSellPrice(SellChestUser user, PlacedChest chest, ItemStack itemStack);

    double getSellPrice(SellChestUser user, PlacedChest chest, ItemStack itemStack, int amount);
}
