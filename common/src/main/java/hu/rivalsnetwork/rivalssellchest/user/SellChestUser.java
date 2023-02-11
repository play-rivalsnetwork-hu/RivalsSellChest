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
    private int loop;

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

    public SellChestUser setPlacedChests(List<PlacedChest> placedChests) {
        this.placedChests = placedChests;
        return this;
    }

    public int chestAmount() {
        return chestAmount;
    }

    public SellChestUser setChestAmount(int chestAmount) {
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
        for (PlacedChest chest : placedChests) {
            set("money", loop, chest.money());
            set("items-sold", loop, chest.itemsSold());
            set("autosell", loop, chest.autoSellEnabled());
            set("chunk-collector", loop, chest.chunkCollectEnabled());
            set("bank", loop, chest.bank());
            set("type", loop, chest.abstractChest().name());
            set("location", loop, LocationSerializer.serialize(chest.location()));
            loop++;
        }
        loop = 0;

        try {
            file.save();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public void load() {
        UserFileHandler.createFile(this);
        PlacedChestLoader.load(this);
        setBoost(file.getDouble("boost"));
        setName(file.getString("name"));
        setChestAmount(file.getSection("chests").getKeys().size());

        Users.addUser(this);
    }

    public void unload() {
        save();
        Users.removeUser(this);
    }

    private void set(String string, int i, Object obj) {
        file.set("chests." + i + "." + string, obj);
    }
}
