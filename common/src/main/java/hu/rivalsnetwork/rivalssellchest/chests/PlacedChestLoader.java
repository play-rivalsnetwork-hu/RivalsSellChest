package hu.rivalsnetwork.rivalssellchest.chests;

import dev.dejvokep.boostedyaml.YamlDocument;
import hu.rivalsnetwork.rivalssellchest.config.UserMadeConfig;
import hu.rivalsnetwork.rivalssellchest.user.SellChestUser;
import hu.rivalsnetwork.rivalssellchest.config.serializer.LocationSerializer;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class PlacedChestLoader {

    public static void load(@NotNull SellChestUser user) {
        List<PlacedChest> placedChests = new ArrayList<>();
        if (user.file().getSection("chests") == null) return;
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
}
