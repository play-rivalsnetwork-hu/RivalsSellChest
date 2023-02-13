package hu.rivalsnetwork.rivalssellchest.chests;

import dev.dejvokep.boostedyaml.YamlDocument;
import hu.rivalsnetwork.rivalssellchest.config.serializer.LocationSerializer;
import hu.rivalsnetwork.rivalssellchest.nms.NMSSetup;
import hu.rivalsnetwork.rivalssellchest.provider.prices.PricesProviderLoader;
import hu.rivalsnetwork.rivalssellchest.user.SellChestUser;
import hu.rivalsnetwork.rivalssellchest.user.Users;
import hu.rivalsnetwork.rivalssellchest.util.MessageUtil;
import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.List;
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
    private long itemsSold;

    public double money() {
        return money;
    }

    public PlacedChest setMoney(double money) {
        this.money = money;
        return this;
    }

    public long itemsSold() {
        return itemsSold;
    }

    public PlacedChest setItemsSold(long itemsSold) {
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

    public void tick() {
        List<ItemStack> items = NMSSetup.getHandler().getEntities(location);
        double amountToGive = 0;
        for (ItemStack item : items) {
            amountToGive = amountToGive + PricesProviderLoader.getProvider().getSellPrice(Users.getUser(this), this, item, item.getAmount());
        }

        MessageUtil.debugMessage("Giving " + amountToGive + " coins to " + ownerName);
    }

    public void serialize(int number) {
        SellChestUser user = Users.getUser(ownerUUID);
        if (user == null) return;
        YamlDocument file = user.file();
        MessageUtil.debugMessage(toString());

        set(file, "money", number, money);
        set(file, "items-sold", number, itemsSold);
        set(file, "autosell", number, autoSellEnabled);
        set(file, "chunk-collector", number, chunkCollectEnabled);
        set(file, "bank", number, bank);
        set(file, "type", number, abstractChest.name());
        set(file, "location", number, LocationSerializer.serialize(location));
    }

    private void set(@NotNull YamlDocument file, @NotNull String string, int i, @NotNull Object obj) {
        SellChestUser user = Users.getUser(ownerUUID);
        if (user == null) return;
        MessageUtil.debugMessage("Setting chests." + i + "." + string + " to: " + obj);
        file.set("chests." + i + "." + string, obj);
        user.setFile(file);
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
                ", money=" + money +
                ", itemsSold=" + itemsSold +
                '}';
    }
}
