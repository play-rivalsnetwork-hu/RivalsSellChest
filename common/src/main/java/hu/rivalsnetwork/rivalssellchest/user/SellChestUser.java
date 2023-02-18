package hu.rivalsnetwork.rivalssellchest.user;

import dev.dejvokep.boostedyaml.YamlDocument;
import hu.rivalsnetwork.rivalssellchest.chests.PlacedChest;
import hu.rivalsnetwork.rivalssellchest.chests.PlacedChestLoader;
import hu.rivalsnetwork.rivalssellchest.util.MessageUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@SuppressWarnings("UnusedReturnValue")
public class SellChestUser extends OfflineSellChestUser {
    private final UUID uuid;
    private String name;
    private double boost;
    private List<PlacedChest> placedChests;
    private int chestAmount;
    private YamlDocument file;
    private int loop;
    private Player player;

    public SellChestUser(UUID uuid) {
        super(uuid);
        this.uuid = uuid;
    }

    public UUID uuid() {
        return uuid;
    }

    public String name() {
        return name;
    }

    public SellChestUser setName(String name) {
        this.name = name;
        return this;
    }

    public double boost() {
        return boost;
    }

    public SellChestUser setBoost(double boost) {
        this.boost = boost;
        return this;
    }

    public List<PlacedChest> placedChests() {
        return placedChests;
    }

    public SellChestUser setPlacedChests(List<PlacedChest> placedChests) {
        this.placedChests = placedChests;
        return this;
    }

    public int chestAmount() {
        return chestAmount;
    }

    public SellChestUser setChestAmount(int chestAmount) {
        this.chestAmount = chestAmount;
        return this;
    }

    public YamlDocument file() {
        return file;
    }

    public SellChestUser setFile(YamlDocument file) {
        this.file = file;
        return this;
    }

    public void save() {
        if (placedChests != null) {
            file.set("chests", null);

            for (PlacedChest chest : placedChests) {
                if (chest == null) continue;
                System.out.println(chest);
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

    private void getNameByUUID() {
        this.player = Bukkit.getPlayer(uuid);
    }

    public void load() {
        MessageUtil.debugMessage("Loading SellChestUser player");
        UserFileHandler.createFile(this);

        MessageUtil.debugMessage("Loading placed chests");
        PlacedChestLoader.load(this);

        MessageUtil.debugMessage("Getting name by UUID");
        getNameByUUID();

        MessageUtil.debugMessage("Writing to file");

        setBoost(file.getDouble("boost", 1.0));
        setName(file.getString("name", player.getName()));
        setChestAmount(file.getSection("chests") == null ? 0 : file.getSection("chests").getKeys().size());

        MessageUtil.debugMessage("Adding user");
        Users.addUser(this);
    }

    public void unload() {
        MessageUtil.debugMessage("Saving");
        save();
        MessageUtil.debugMessage("Removing user");
        Users.removeUser(this);
    }

    public Player player() {
        return player;
    }

    @Override
    public String toString() {
        return "SellChestUser{" +
                "uuid=" + uuid +
                ", name='" + name + '\'' +
                ", boost=" + boost +
                ", placedChests=" + placedChests +
                ", chestAmount=" + chestAmount +
                ", file=" + file +
                ", loop=" + loop +
                ", player=" + player +
                '}';
    }
}
