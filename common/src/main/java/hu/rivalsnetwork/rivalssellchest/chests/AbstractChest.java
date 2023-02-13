package hu.rivalsnetwork.rivalssellchest.chests;

import dev.dejvokep.boostedyaml.YamlDocument;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;

public class AbstractChest {
    private String name;
    private long sellInterval;
    private double boost;
    private boolean persistentStats;
    private double hologramHeight;
    private long hologramUpdateTicks;
    private boolean hologramEnabled;
    private List<?> hologramLines;
    private YamlDocument file;
    private NamespacedKey key;
    private ItemStack itemStack;

    public ItemStack itemStack() {
        return itemStack;
    }

    public AbstractChest setItemStack(ItemStack itemStack) {
        this.itemStack = itemStack;
        return this;
    }

    public NamespacedKey key() {
        return key;
    }

    public AbstractChest setKey(NamespacedKey key) {
        this.key = key;
        return this;
    }

    public String name() {
        return name;
    }

    public AbstractChest setName(String name) {
        this.name = name;
        return this;
    }

    public long sellInterval() {
        return sellInterval;
    }

    public AbstractChest setSellInterval(long sellInterval) {
        this.sellInterval = sellInterval;
        return this;
    }

    public double boost() {
        return boost;
    }

    public AbstractChest setBoost(double boost) {
        this.boost = boost;
        return this;
    }

    public boolean persistentStats() {
        return persistentStats;
    }

    public AbstractChest setPersistentStats(boolean persistentStats) {
        this.persistentStats = persistentStats;
        return this;
    }

    public double hologramHeight() {
        return hologramHeight;
    }

    public AbstractChest setHologramHeight(double hologramHeight) {
        this.hologramHeight = hologramHeight;
        return this;
    }

    public long hologramUpdateTicks() {
        return hologramUpdateTicks;
    }

    public AbstractChest setHologramUpdateTicks(long hologramUpdateTicks) {
        this.hologramUpdateTicks = hologramUpdateTicks;
        return this;
    }

    public boolean hologramEnabled() {
        return hologramEnabled;
    }

    public AbstractChest setHologramEnabled(boolean hologramEnabled) {
        this.hologramEnabled = hologramEnabled;
        return this;
    }

    public List<?> hologramLines() {
        return hologramLines;
    }

    public AbstractChest setHologramLines(List<?> hologramLines) {
        this.hologramLines = hologramLines;
        return this;
    }

    public YamlDocument file() {
        return file;
    }

    public AbstractChest setFile(YamlDocument file) {
        this.file = file;
        return this;
    }

    public void createItemStack() {
        List<Component> lore = new ArrayList<>();
        file.getStringList("item.lore").forEach(line -> lore.add(MiniMessage.miniMessage().deserialize(line)));

        ItemStack item = new ItemStack(Material.CHEST);
        ItemMeta meta = item.getItemMeta();
        meta.displayName(MiniMessage.miniMessage().deserialize(file.getString("item.name")));
        meta.lore(lore);
        meta.getPersistentDataContainer().set(key, PersistentDataType.BYTE, (byte) 0);
        item.setItemMeta(meta);

        itemStack = item;
    }

    @Override
    public String toString() {
        return "AbstractChest{" +
                "name='" + name + '\'' +
                ", sellInterval=" + sellInterval +
                ", boost=" + boost +
                ", persistentStats=" + persistentStats +
                ", hologramHeight=" + hologramHeight +
                ", hologramUpdateTicks=" + hologramUpdateTicks +
                ", hologramEnabled=" + hologramEnabled +
                ", hologramLines=" + hologramLines +
                ", file=" + file +
                ", key=" + key +
                '}';
    }
}
