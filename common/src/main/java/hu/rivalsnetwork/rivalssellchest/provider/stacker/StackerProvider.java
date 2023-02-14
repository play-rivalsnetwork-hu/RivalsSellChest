package hu.rivalsnetwork.rivalssellchest.provider.stacker;

import org.bukkit.entity.Item;

public interface StackerProvider {

    int getAmount(Item item);

    void setItemAmount(Item item, int amount);
}
