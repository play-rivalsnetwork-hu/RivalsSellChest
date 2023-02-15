package hu.rivalsnetwork.rivalssellchest.util;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class PlayerUtil {

    @NotNull
    public static CompletableFuture<OfflinePlayer> getOfflinePlayer(UUID uuid) {
        return CompletableFuture.supplyAsync(() -> Bukkit.getOfflinePlayer(uuid));
    }
}
