package hu.rivalsnetwork.rivalssellchest.provider.stacker.impl;

import com.bgsoftware.wildstacker.api.WildStackerAPI;
import hu.rivalsnetwork.rivalssellchest.provider.stacker.StackerProvider;
import org.bukkit.entity.Item;

public class WildStackerStackerProvider implements StackerProvider {

    @Override
    public int getAmount(Item item) {
        return WildStackerAPI.getItemAmount(item);
    }

    @Override
    public void setItemAmount(Item item, int amount) {
        WildStackerAPI.getStackedItem(item).setStackAmount(amount, true);
    }
}
