package hu.rivalsnetwork.rivalssellchest.chests;

import dev.dejvokep.boostedyaml.YamlDocument;
import org.bukkit.NamespacedKey;

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
