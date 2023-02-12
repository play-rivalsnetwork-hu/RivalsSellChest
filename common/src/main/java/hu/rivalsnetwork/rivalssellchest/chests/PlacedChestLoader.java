package hu.rivalsnetwork.rivalssellchest.chests;

import dev.dejvokep.boostedyaml.YamlDocument;
import hu.rivalsnetwork.rivalssellchest.RivalsSellChestPlugin;
import hu.rivalsnetwork.rivalssellchest.config.ConfigLoader;
import hu.rivalsnetwork.rivalssellchest.config.UserMadeConfig;
import hu.rivalsnetwork.rivalssellchest.config.serializer.LocationSerializer;
import hu.rivalsnetwork.rivalssellchest.user.SellChestUser;
import hu.rivalsnetwork.rivalssellchest.util.MessageUtil;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;

public class PlacedChestLoader {
    private static final HashMap<Location, PlacedChest> locationPlacedChestHashMap = new HashMap<>();

    public static void load(@NotNull SellChestUser user) {
        List<PlacedChest> placedChests = new ArrayList<>();

        if (user.file().getSection("chests") == null) {
            user.setPlacedChests(placedChests);
            return;
        }

        for (Object chest : user.file().getSection("chests").getKeys()) {
            YamlDocument config = user.file();
            PlacedChest placedChest = new PlacedChest()
                    .setBank(config.getBoolean("chests." + chest + ".bank"))
                    .setAbstractChest(UserMadeConfig.getChests().get(config.getString("chests." + chest + ".type")))
                    .setAutoSellEnabled(config.getBoolean("chests." + chest + ".autosell"))
                    .setLocation(LocationSerializer.deserialize("chests." + chest + ".location", config))
                    .setItemsSold(config.getLong("chests." + chest + ".items-sold"))
                    .setMoney(config.getDouble("chests." + chest + ".money"))
                    .setChunkCollectEnabled(config.getBoolean("chests." + chest + ".chunk-collector"));

            placedChests.add(placedChest);
        }
        user.setPlacedChests(placedChests);
    }

    public static void load(@NotNull YamlDocument config) {
        for (Object chest : config.getSection("chests").getKeys()) {
            PlacedChest placedChest = new PlacedChest()
                    .setBank(config.getBoolean("chests." + chest + ".bank"))
                    .setAbstractChest(UserMadeConfig.getChests().get(config.getString("chests." + chest + ".type")))
                    .setAutoSellEnabled(config.getBoolean("chests." + chest + ".autosell"))
                    .setLocation(LocationSerializer.deserialize("chests." + chest + ".location", config))
                    .setItemsSold(config.getLong("chests." + chest + ".items-sold"))
                    .setMoney(config.getDouble("chests." + chest + ".money"))
                    .setChunkCollectEnabled(config.getBoolean("chests." + chest + ".chunk-collector"));

            locationPlacedChestHashMap.put(placedChest.location(), placedChest);
        }
    }

    public static void loadAll() {
        final File file = new File(RivalsSellChestPlugin.getInstance().getDataFolder(), "/playerdata/");
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
