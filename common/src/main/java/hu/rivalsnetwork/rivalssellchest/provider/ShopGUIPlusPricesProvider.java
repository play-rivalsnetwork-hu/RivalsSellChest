package hu.rivalsnetwork.rivalssellchest.provider;

import com.google.common.base.Preconditions;
import hu.rivalsnetwork.rivalssellchest.chests.PlacedChest;
import hu.rivalsnetwork.rivalssellchest.user.SellChestUser;
import net.brcdev.shopgui.ShopGuiPlusApi;
import org.bukkit.inventory.ItemStack;

public class ShopGUIPlusPricesProvider implements PricesProvider {

    @Override
    public double getSellPrice(ItemStack itemStack) {
        itemStack.setAmount(0);
        return ShopGuiPlusApi.getItemStackPriceSell(itemStack);
    }

    @Override
    public double getSellPrice(ItemStack itemStack, int amount) {
        itemStack.setAmount(amount);
        return ShopGuiPlusApi.getItemStackPriceSell(itemStack);
    }

    @Override
    public double getSellPrice(SellChestUser user, ItemStack itemStack) {
        if (user == null) return getSellPrice(itemStack);
        itemStack.setAmount(1);
        return user.boost() * ShopGuiPlusApi.getItemStackPriceSell(user.player().getPlayer(), itemStack);
    }

    @Override
    public double getSellPrice(SellChestUser user, ItemStack itemStack, int amount) {
        if (user == null) return getSellPrice(itemStack);
        itemStack.setAmount(amount);
        return user.boost() * ShopGuiPlusApi.getItemStackPriceSell(user.player().getPlayer(), itemStack);
    }

    @Override
    public double getSellPrice(SellChestUser user, PlacedChest chest, ItemStack itemStack) {
        if (user == null) return getSellPrice(itemStack);
        itemStack.setAmount(1);
        return user.boost() * chest.abstractChest().boost() * ShopGuiPlusApi.getItemStackPriceSell(user.player().getPlayer(), itemStack);
    }

    @Override
    public double getSellPrice(SellChestUser user, PlacedChest chest, ItemStack itemStack, int amount) {
        if (user == null) return getSellPrice(itemStack);
        itemStack.setAmount(amount);
        return user.boost() * chest.abstractChest().boost() * ShopGuiPlusApi.getItemStackPriceSell(user.player().getPlayer(), itemStack);
    }
}
