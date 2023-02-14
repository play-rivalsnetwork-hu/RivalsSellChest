package hu.rivalsnetwork.rivalssellchest.provider.stacker.impl;

import dev.rosewood.rosestacker.api.RoseStackerAPI;
import dev.rosewood.rosestacker.stack.StackedItem;
import hu.rivalsnetwork.rivalssellchest.provider.stacker.StackerProvider;
import org.bukkit.entity.Item;

public class RoseStackerStackerProvider implements StackerProvider {

    @Override
    public int getAmount(Item item) {
        StackedItem stackedItem = RoseStackerAPI.getInstance().getStackedItem(item);
        if (stackedItem == null) return 0;
        return stackedItem.getStackSize();
    }

    @Override
    public void setItemAmount(Item item, int amount) {
        StackedItem stackedItem = RoseStackerAPI.getInstance().getStackedItem(item);
        if (stackedItem == null) return;
        stackedItem.setStackSize(amount);
    }
}
