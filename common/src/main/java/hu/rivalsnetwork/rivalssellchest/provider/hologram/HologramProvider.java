package hu.rivalsnetwork.rivalssellchest.provider.hologram;

import eu.decentsoftware.holograms.api.holograms.Hologram;
import org.bukkit.Location;

import java.util.List;

public interface HologramProvider {

    Hologram createHologram(String name, Location location, List<String> lines);

    void removeHologram(Hologram hologram);
}
