package hu.rivalsnetwork.rivalssellchest.user;

import dev.dejvokep.boostedyaml.YamlDocument;
import hu.rivalsnetwork.rivalssellchest.chests.PlacedChest;
import hu.rivalsnetwork.rivalssellchest.chests.PlacedChestLoader;
import hu.rivalsnetwork.rivalssellchest.util.serializer.LocationSerializer;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class SellChestUser {
    private final UUID uuid;
    private String name;
    private double boost;
    private List<PlacedChest> placedChests;
    private int chestAmount;
    private YamlDocument file;

    public SellChestUser(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID uuid() {
        return uuid;
    }

    public String name() {
        return name;
    }

    public SellChestUser setName(String name) {
        this.name = name;
        return this;
    }

    public double boost() {
        return boost;
    }

    public SellChestUser setBoost(double boost) {
        this.boost = boost;
        return this;
    }

    public List<PlacedChest> placedChests() {
        return placedChests;
    }

    public SellChestUser setChests(List<PlacedChest> placedChests) {
        this.placedChests = placedChests;
        return this;
    }

    public int chestAmount() {
        return chestAmount;
    }

    public SellChestUser setPlacedChests(int chestAmount) {
        this.chestAmount = chestAmount;
        return this;
    }

    public YamlDocument file() {
        return file;
    }

    public SellChestUser setFile(YamlDocument file) {
        this.file = file;
        return this;
    }

    public void save() {
        int i = 0;
        for (PlacedChest chest : placedChests) {
            set("money", i, chest.money());
            set("items-sold", i, chest.itemsSold());
            set("autosell", i, chest.autoSellEnabled());
            set("chunk-collector", i, chest.chunkCollectEnabled());
            set("bank", i, chest.bank());
            set("type", i, chest.abstractChest().name());
            set("location", i, LocationSerializer.serialize(chest.location()));
            i++;
        }

        try {
            file.save();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public void load() {
        UserFileHandler.createFile(this);
        PlacedChestLoader.load(this);
    }

    private void set(String string, int i, Object obj) {
        file.set("chests." + i + "." + string, obj);
    }
}
