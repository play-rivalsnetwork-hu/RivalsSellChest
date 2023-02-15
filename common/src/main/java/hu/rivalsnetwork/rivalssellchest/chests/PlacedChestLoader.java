package hu.rivalsnetwork.rivalssellchest.chests;

import dev.dejvokep.boostedyaml.YamlDocument;
import hu.rivalsnetwork.rivalssellchest.RivalsSellChestPlugin;
import hu.rivalsnetwork.rivalssellchest.config.ConfigLoader;
import hu.rivalsnetwork.rivalssellchest.config.UserMadeConfig;
import hu.rivalsnetwork.rivalssellchest.config.serializer.LocationSerializer;
import hu.rivalsnetwork.rivalssellchest.user.OfflineSellChestUser;
import hu.rivalsnetwork.rivalssellchest.user.SellChestUser;
import hu.rivalsnetwork.rivalssellchest.util.MessageUtil;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;

public class PlacedChestLoader {

    public static void load(@NotNull SellChestUser user) {
        List<PlacedChest> placedChests = new ArrayList<>();

        if (user.file().getSection("chests") == null) {
            user.setPlacedChests(placedChests);
            return;
        }

        for (Object chest : user.file().getSection("chests").getKeys()) {
            YamlDocument config = user.file();
            PlacedChest placedChest = new PlacedChest()
                    .setOwnerName(config.getString("name"))
                    .setOwnerUUID(UUID.fromString(config.getString("uuid")))
                    .setBank(config.getBoolean("chests." + chest + ".bank"))
                    .setAbstractChest(UserMadeConfig.getChests().get(config.getString("chests." + chest + ".type")))
                    .setAutoSellEnabled(config.getBoolean("chests." + chest + ".autosell"))
                    .setLocation(LocationSerializer.deserialize("chests." + chest + ".location", config))
                    .setItemsSold(config.getLong("chests." + chest + ".items-sold"))
                    .setMoney(config.getDouble("chests." + chest + ".money"))
                    .setChunkCollectEnabled(config.getBoolean("chests." + chest + ".chunk-collector"))
                    .setPlayer(Bukkit.getOfflinePlayer(UUID.fromString(config.getString("uuid"))));

            MessageUtil.debugMessage(placedChest.toString());

            placedChests.add(placedChest);
        }
        user.setPlacedChests(placedChests);
    }

    public static void load(@NotNull OfflineSellChestUser user) {
        List<PlacedChest> placedChests = new ArrayList<>();

        if (user.file().getSection("chests") == null) {
            user.setPlacedChests(placedChests);
            return;
        }

        for (Object chest : user.file().getSection("chests").getKeys()) {
            YamlDocument config = user.file();
            PlacedChest placedChest = new PlacedChest()
                    .setOwnerName(config.getString("name"))
                    .setOwnerUUID(UUID.fromString(config.getString("uuid")))
                    .setBank(config.getBoolean("chests." + chest + ".bank"))
                    .setAbstractChest(UserMadeConfig.getChests().get(config.getString("chests." + chest + ".type")))
                    .setAutoSellEnabled(config.getBoolean("chests." + chest + ".autosell"))
                    .setLocation(LocationSerializer.deserialize("chests." + chest + ".location", config))
                    .setItemsSold(config.getLong("chests." + chest + ".items-sold"))
                    .setMoney(config.getDouble("chests." + chest + ".money"))
                    .setChunkCollectEnabled(config.getBoolean("chests." + chest + ".chunk-collector"))
                    .setPlayer(Bukkit.getOfflinePlayer(UUID.fromString(config.getString("uuid"))));

            MessageUtil.debugMessage(placedChest.toString());

            placedChests.add(placedChest);
        }
        user.setPlacedChests(placedChests);
    }

    public static void load(@NotNull YamlDocument config) {
        if (config.getSection("chests") == null) return;

        for (Object chest : config.getSection("chests").getKeys()) {
            PlacedChest placedChest = new PlacedChest()
                    .setOwnerName(config.getString("name"))
                    .setOwnerUUID(UUID.fromString(config.getString("uuid")))
                    .setBank(config.getBoolean("chests." + chest + ".bank"))
                    .setAbstractChest(UserMadeConfig.getChests().get(config.getString("chests." + chest + ".type")))
                    .setAutoSellEnabled(config.getBoolean("chests." + chest + ".autosell"))
                    .setLocation(LocationSerializer.deserialize("chests." + chest + ".location", config))
                    .setItemsSold(config.getLong("chests." + chest + ".items-sold"))
                    .setMoney(config.getDouble("chests." + chest + ".money"))
                    .setChunkCollectEnabled(config.getBoolean("chests." + chest + ".chunk-collector"))
                    .setPlayer(Bukkit.getOfflinePlayer(UUID.fromString(config.getString("uuid"))))
                    .setFile(config);

            MessageUtil.debugMessage(placedChest.toString());

            ChestTicker.getChestsToTick().put(placedChest.location(), placedChest);
        }
    }

    public static void loadAll() {
        final File file = new File(RivalsSellChestPlugin.getInstance().getDataFolder(), "/playerdata");
        File[] files = file.listFiles();

        if (files == null) {
            MessageUtil.log(Level.INFO, "No playerdata files found!");
            return;
        }

        for (File playerDataFiles : files) {
            if (!playerDataFiles.toString().contains(".yaml") && !playerDataFiles.toString().contains(".yml")) continue;
            load(ConfigLoader.create(playerDataFiles));
        }
    }
}
