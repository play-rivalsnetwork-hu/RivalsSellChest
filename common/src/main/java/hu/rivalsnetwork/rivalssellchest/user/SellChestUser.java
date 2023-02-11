package hu.rivalsnetwork.rivalssellchest.user;

import dev.dejvokep.boostedyaml.YamlDocument;
import hu.rivalsnetwork.rivalssellchest.chests.PlacedChest;

import java.util.List;
import java.util.UUID;

public class SellChestUser {
    private final UUID uuid;
    private String name;
    private double boost;
    private List<PlacedChest> chests;
    private int placedChests;
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

    public List<PlacedChest> chests() {
        return chests;
    }

    public SellChestUser setChests(List<PlacedChest> chests) {
        this.chests = chests;
        return this;
    }

    public int placedChests() {
        return placedChests;
    }

    public SellChestUser setPlacedChests(int placedChests) {
        this.placedChests = placedChests;
        return this;
    }

    public YamlDocument file() {
        return file;
    }

    public SellChestUser setFile(YamlDocument file) {
        this.file = file;
        return this;
    }

    @Override
    public String toString() {
        return "SellChestUser{" +
                "uuid=" + uuid +
                ", name='" + name + '\'' +
                ", boost=" + boost +
                ", chests=" + chests +
                ", placedChests=" + placedChests +
                ", file=" + file +
                '}';
    }
}
