package hu.rivalsnetwork.rivalssellchest.chests;

import com.google.common.base.Objects;
import hu.rivalsnetwork.rivalssellchest.user.SellChestUser;
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
    private SellChestUser owner;

    public SellChestUser owner() {
        return owner;
    }

    public PlacedChest setOwner(SellChestUser owner) {
        this.owner = owner;
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

    @Override
    public String toString() {
        return "PlacedChest{" +
                "ownerUUID=" + ownerUUID +
                ", ownerName='" + ownerName + '\'' +
                ", location=" + location +
                ", autoSellEnabled=" + autoSellEnabled +
                ", chunkCollectEnabled=" + chunkCollectEnabled +
                ", bank=" + bank +
                ", abstractChest=" + abstractChest +
                ", owner=" + owner +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlacedChest that)) return false;
        return autoSellEnabled == that.autoSellEnabled && chunkCollectEnabled == that.chunkCollectEnabled && bank == that.bank && Objects.equal(ownerUUID, that.ownerUUID) && Objects.equal(ownerName, that.ownerName) && Objects.equal(location, that.location) && Objects.equal(abstractChest, that.abstractChest) && Objects.equal(owner, that.owner);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(ownerUUID, ownerName, location, autoSellEnabled, chunkCollectEnabled, bank, abstractChest, owner);
    }
}
