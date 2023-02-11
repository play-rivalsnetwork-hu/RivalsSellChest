package hu.rivalsnetwork.rivalssellchest.command;

import hu.rivalsnetwork.rivalssellchest.nms.NMSSetup;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SellChestCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        List<Entity> entities = NMSSetup.getHandler().getEntities(((Player) sender).getChunk());

        sender.sendMessage(entities.size() + " ItemEntity found");
        return true;
    }
}
