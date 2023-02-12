package hu.rivalsnetwork.rivalssellchest.provider;

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
        itemStack.setAmount(1);
        return user.boost() * ShopGuiPlusApi.getItemStackPriceSell(user.player().getPlayer(), itemStack);
    }

    @Override
    public double getSellPrice(SellChestUser user, ItemStack itemStack, int amount) {
        itemStack.setAmount(amount);
        return user.boost() * ShopGuiPlusApi.getItemStackPriceSell(user.player().getPlayer(), itemStack);
    }
}
