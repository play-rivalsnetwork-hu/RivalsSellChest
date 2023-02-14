package hu.rivalsnetwork.rivalssellchest.provider.stacker.impl;

import hu.rivalsnetwork.rivalssellchest.provider.stacker.StackerProvider;
import org.bukkit.entity.Item;

public class DefaultStackerProvider implements StackerProvider {

    @Override
    public int getAmount(Item item) {
        return item.getItemStack().getAmount();
    }

    @Override
    public void setItemAmount(Item item, int amount) {
        item.getItemStack().setAmount(amount);
    }
}
