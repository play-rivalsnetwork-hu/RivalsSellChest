package hu.rivalsnetwork.rivalssellchest.provider;

import hu.rivalsnetwork.rivalssellchest.chests.PlacedChest;
import hu.rivalsnetwork.rivalssellchest.config.PricesConfig;
import hu.rivalsnetwork.rivalssellchest.user.SellChestUser;
import org.bukkit.inventory.ItemStack;

import java.util.Locale;

public class LocalPricesProvider implements PricesProvider {

    @Override
    public double getSellPrice(ItemStack itemStack) {
        return PricesConfig.getConfig().getDouble("items." + itemStack.getType().toString().toLowerCase(Locale.ENGLISH), 0.0D);
    }

    @Override
    public double getSellPrice(ItemStack itemStack, int amount) {
        return PricesConfig.getConfig().getDouble("items." + itemStack.getType().toString().toLowerCase(Locale.ENGLISH), 0.0D) * amount;
    }

    @Override
    public double getSellPrice(SellChestUser user, ItemStack itemStack) {
        if (user == null) return getSellPrice(itemStack);
        return user.boost() * PricesConfig.getConfig().getDouble("items." + itemStack.getType().toString().toLowerCase(Locale.ENGLISH), 0.0D);
    }

    @Override
    public double getSellPrice(SellChestUser user, ItemStack itemStack, int amount) {
        if (user == null) return getSellPrice(itemStack);
        return user.boost() * PricesConfig.getConfig().getDouble("items." + itemStack.getType().toString().toLowerCase(Locale.ENGLISH), 0.0D) * amount;
    }

    @Override
    public double getSellPrice(SellChestUser user, PlacedChest chest, ItemStack itemStack) {
        if (user == null) return getSellPrice(itemStack);
        return user.boost() * chest.abstractChest().boost() * PricesConfig.getConfig().getDouble("items." + itemStack.getType().toString().toLowerCase(Locale.ENGLISH), 0.0D);
    }

    @Override
    public double getSellPrice(SellChestUser user, PlacedChest chest, ItemStack itemStack, int amount) {
        if (user == null) return getSellPrice(itemStack);
        return user.boost() * chest.abstractChest().boost() * amount * PricesConfig.getConfig().getDouble("items." + itemStack.getType().toString().toLowerCase(Locale.ENGLISH), 0.0D);
    }
}
