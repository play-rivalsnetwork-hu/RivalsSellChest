package hu.rivalsnetwork.rivalssellchest.command;

import hu.rivalsnetwork.rivalssellchest.chests.AbstractChest;
import hu.rivalsnetwork.rivalssellchest.chests.ChestTicker;
import hu.rivalsnetwork.rivalssellchest.config.UserMadeConfig;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SellChestCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        for (AbstractChest chest : UserMadeConfig.getChests().values()) {
            sender.sendMessage(chest.name());
        }

        if (!(sender instanceof Player)) return true;
//        List<Entity> entities = NMSSetup.getHandler().getEntities(((Player) sender).getChunk());
//
//        sender.sendMessage(entities.size() + " ItemEntity found");

//        ChestTicker.chunkChestMap.put(((Player) sender).getChunk(), UserMadeConfig.getChests().get(0));
        return true;
    }
}
