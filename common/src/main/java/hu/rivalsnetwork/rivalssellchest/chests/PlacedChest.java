package hu.rivalsnetwork.rivalssellchest.chests;

import org.bukkit.Location;

import java.util.UUID;

public class PlacedChest {
    private UUID ownerUUID;
    private String ownerName;
    private Location location;
    private boolean autoSellEnabled;
    private boolean chunkCollectEnabled;
    private boolean bank;
    private AbstractChest abstractChest;
    private double money;
    private int itemsSold;

    public double money() {
        return money;
    }

    public PlacedChest setMoney(double money) {
        this.money = money;
        return this;
    }

    public int itemsSold() {
        return itemsSold;
    }

    public PlacedChest setItemsSold(int itemsSold) {
        this.itemsSold = itemsSold;
        return this;
    }

    public AbstractChest abstractChest() {
        return abstractChest;
    }

    public PlacedChest setAbstractChest(AbstractChest abstractChest) {
        this.abstractChest = abstractChest;
        return this;
    }

    public UUID ownerUUID() {
        return ownerUUID;
    }

    public PlacedChest setOwnerUUID(UUID ownerUUID) {
        this.ownerUUID = ownerUUID;
        return this;
    }

    public String ownerName() {
        return ownerName;
    }

    public PlacedChest setOwnerName(String ownerName) {
        this.ownerName = ownerName;
        return this;
    }

    public Location location() {
        return location;
    }

    public PlacedChest setLocation(Location location) {
        this.location = location;
        return this;
    }

    public boolean autoSellEnabled() {
        return autoSellEnabled;
    }

    public PlacedChest setAutoSellEnabled(boolean autoSellEnabled) {
        this.autoSellEnabled = autoSellEnabled;
        return this;
    }

    public boolean chunkCollectEnabled() {
        return chunkCollectEnabled;
    }

    public PlacedChest setChunkCollectEnabled(boolean chunkCollectEnabled) {
        this.chunkCollectEnabled = chunkCollectEnabled;
        return this;
    }

    public boolean bank() {
        return bank;
    }

    public PlacedChest setBank(boolean bank) {
        this.bank = bank;
        return this;
    }
}
