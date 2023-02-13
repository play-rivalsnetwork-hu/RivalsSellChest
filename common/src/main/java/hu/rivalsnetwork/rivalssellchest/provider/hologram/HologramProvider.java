package hu.rivalsnetwork.rivalssellchest.provider.hologram;

import eu.decentsoftware.holograms.api.holograms.Hologram;
import hu.rivalsnetwork.rivalssellchest.chests.AbstractChest;
import org.bukkit.Location;

import java.util.HashMap;
import java.util.List;

public interface HologramProvider {

    HashMap<AbstractChest, Hologram> map();

    Hologram createHologram(String name, Location location, List<String> lines);

    void removeHologram(Hologram hologram);
}
