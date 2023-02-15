package hu.rivalsnetwork.rivalssellchest.chests;

import dev.dejvokep.boostedyaml.YamlDocument;
import eu.decentsoftware.holograms.api.holograms.Hologram;
import hu.rivalsnetwork.rivalssellchest.config.serializer.LocationSerializer;
import hu.rivalsnetwork.rivalssellchest.nms.NMSSetup;
import hu.rivalsnetwork.rivalssellchest.provider.economy.EconomyProviderLoader;
import hu.rivalsnetwork.rivalssellchest.provider.hologram.HologramProviderLoader;
import hu.rivalsnetwork.rivalssellchest.provider.prices.PricesProviderLoader;
import hu.rivalsnetwork.rivalssellchest.provider.stacker.StackerProviderLoader;
import hu.rivalsnetwork.rivalssellchest.user.SellChestUser;
import hu.rivalsnetwork.rivalssellchest.user.Users;
import hu.rivalsnetwork.rivalssellchest.util.MessageUtil;
import hu.rivalsnetwork.rivalssellchest.util.StringUtils;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

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
    private OfflinePlayer player;
    private Hologram hologram;

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

    public OfflinePlayer player() {
        return player;
    }

    public PlacedChest setPlayer(OfflinePlayer player) {
        this.player = player;
        return this;
    }

    private void updateHologram() {
        if (hologram != null) this.hologram.destroy();
        this.hologram = HologramProviderLoader.getProvider().createHologram(this.ownerName + LocationSerializer.serialize(this.location), this.location, StringUtils.replaceInLines(abstractChest.hologramLines(), this));
    }

    public void tick() {
        MessageUtil.debugMessage("Before: " + this);
        HashMap<ItemStack, Item> items = NMSSetup.getHandler().getEntities(location);
        AtomicReference<Double> amountToGive = new AtomicReference<>((double) 0);
        items.forEach((itemStack, item) -> amountToGive.set(amountToGive.get() + PricesProviderLoader.getProvider().getSellPrice(Users.getUser(this), this, itemStack, StackerProviderLoader.provider().getAmount(item))));

        money = money + amountToGive.get();
        itemsSold = itemsSold + items.size();
        MessageUtil.debugMessage("After: " + this);

        updateHologram();
        EconomyProviderLoader.getProvider().giveBalance(player, amountToGive.get());
        if (player.getPlayer() == null) return;
        if (amountToGive.get() <= 0.0D) return;
        player.getPlayer().sendActionBar(MiniMessage.miniMessage().deserialize("<white>You gained <green>%money% <white>in the last 10 seconds!".replace("%money%", String.valueOf(amountToGive.get()))));
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
