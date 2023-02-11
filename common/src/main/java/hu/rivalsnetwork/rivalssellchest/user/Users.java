package hu.rivalsnetwork.rivalssellchest.user;

import com.google.common.collect.HashBiMap;
import hu.rivalsnetwork.rivalssellchest.chests.PlacedChest;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class Users {
    private static final HashBiMap<UUID, SellChestUser> SELL_CHEST_USERS = HashBiMap.create();

    public static void addUser(@NotNull SellChestUser sellChestUser) {
        if (SELL_CHEST_USERS.containsKey(sellChestUser.uuid())) return;
        SELL_CHEST_USERS.put(sellChestUser.uuid(), sellChestUser);
    }

    public static void removeUser(@NotNull UUID uuid) {
        SELL_CHEST_USERS.remove(uuid);
    }

    public static void removeUser(@NotNull SellChestUser sellChestUser) {
        SELL_CHEST_USERS.remove(sellChestUser.uuid());
    }

    @Nullable
    public static SellChestUser getUser(@NotNull UUID uuid) {
        return SELL_CHEST_USERS.get(uuid);
    }

    @Nullable
    public static SellChestUser getUser(@NotNull Player player) {
        return SELL_CHEST_USERS.get(player.getUniqueId());
    }

    @Nullable
    public static SellChestUser getUser(@NotNull PlacedChest placedChest) {
        return SELL_CHEST_USERS.get(placedChest.ownerUUID());
    }
}
