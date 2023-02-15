package hu.rivalsnetwork.rivalssellchest.user;

import dev.dejvokep.boostedyaml.YamlDocument;
import hu.rivalsnetwork.rivalssellchest.chests.PlacedChest;
import hu.rivalsnetwork.rivalssellchest.chests.PlacedChestLoader;
import hu.rivalsnetwork.rivalssellchest.util.MessageUtil;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class OfflineSellChestUser {
    private final UUID uuid;
    private String name;
    private double boost;
    private List<PlacedChest> placedChests;
    private YamlDocument file;
    private int loop;
    private OfflinePlayer player;

    public OfflineSellChestUser(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID uuid() {
        return uuid;
    }

    public String name() {
        return name;
    }

    public OfflineSellChestUser setName(String name) {
        this.name = name;
        save();
        return this;
    }

    public double boost() {
        return boost;
    }

    public OfflineSellChestUser setBoost(double boost) {
        this.boost = boost;
        save();
        return this;
    }

    public List<PlacedChest> placedChests() {
        return placedChests;
    }

    public OfflineSellChestUser setPlacedChests(List<PlacedChest> placedChests) {
        this.placedChests = placedChests;
        save();
        return this;
    }

    public YamlDocument file() {
        return file;
    }

    public OfflineSellChestUser setFile(YamlDocument file) {
        this.file = file;
        return this;
    }

    public OfflinePlayer player() {
        return player;
    }

    public OfflineSellChestUser setPlayer(OfflinePlayer player) {
        this.player = player;
        return this;
    }

    public void save() {
        if (placedChests != null) {
            file.set("chests", null);

            for (PlacedChest chest : placedChests) {
                if (chest == null) continue;
                chest.serialize(loop);
                loop++;
            }
            loop = 0;
        }

        file.set("name", name);
        file.set("uuid", uuid);
        file.set("boost", boost);

        try {
            file.save();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public void load() {
        MessageUtil.debugMessage("Creating file");
        UserFileHandler.createFile(this);

        MessageUtil.debugMessage("Loading placed chests");
        PlacedChestLoader.load(this);

        MessageUtil.debugMessage("Getting name by UUID");
        player = Bukkit.getOfflinePlayer(uuid);
        name = player.getName();

        MessageUtil.debugMessage("Writing to file");

        setBoost(file.getDouble("boost", 1.0));
        setName(file.getString("name", player.getName()));

        MessageUtil.debugMessage("Adding offlineuser");
        Users.addOfflineUser(this);
    }

    public void unload() {
        MessageUtil.debugMessage("Saving");
        save();
        MessageUtil.debugMessage("Removing user");
        Users.removeOfflineUser(this);
    }
}
