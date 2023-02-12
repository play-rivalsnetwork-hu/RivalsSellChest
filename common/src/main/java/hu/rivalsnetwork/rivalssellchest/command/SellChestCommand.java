package hu.rivalsnetwork.rivalssellchest.command;

import hu.rivalsnetwork.rivalssellchest.chests.AbstractChest;
import hu.rivalsnetwork.rivalssellchest.config.UserMadeConfig;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SellChestCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof Player p)) return true;
        for (AbstractChest chest : UserMadeConfig.getChests().values()) {
            ItemStack i = new ItemStack(Material.CHEST);
            ItemMeta meta = i.getItemMeta();
            meta.displayName(Component.text("SellChest"));
            meta.lore(List.of(Component.text(""), Component.text("Line2")));
            meta.getPersistentDataContainer().set(chest.key(), PersistentDataType.BYTE, (byte) 0);
            i.setItemMeta(meta);

            p.getInventory().addItem(i);
        }

        return true;
    }
}
