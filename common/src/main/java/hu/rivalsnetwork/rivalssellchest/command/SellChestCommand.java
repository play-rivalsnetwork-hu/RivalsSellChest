package hu.rivalsnetwork.rivalssellchest.command;

import hu.rivalsnetwork.rivalssellchest.chests.AbstractChest;
import hu.rivalsnetwork.rivalssellchest.config.UserMadeConfig;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SellChestCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player p)) return true;

        String chest = args[0];

        AbstractChest abstractChest = UserMadeConfig.getChests().get(chest);

        p.getInventory().addItem(abstractChest.itemStack());
        return true;
    }
}
