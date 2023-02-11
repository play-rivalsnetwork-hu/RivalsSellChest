package hu.rivalsnetwork.rivalssellchest.util.serializer;

import dev.dejvokep.boostedyaml.YamlDocument;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

public class LocationSerializer {

    @NotNull
    public static String serialize(@NotNull Location location) {
        return location.getWorld().getName() + ";" + location.getBlockX() + ";" + location.getBlockY() + ";" + location.getBlockZ();
    }

    @NotNull
    public static Location deserialize(@NotNull String object, @NotNull YamlDocument document) {
        String[] split = document.getString(object).split(";");
        return new Location(Bukkit.getWorld(split[0]), Double.parseDouble(split[1]), Double.parseDouble(split[2]), Double.parseDouble(split[3]));
    }
}
