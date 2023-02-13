package hu.rivalsnetwork.rivalssellchest.provider.hologram.impl;

import eu.decentsoftware.holograms.api.DHAPI;
import eu.decentsoftware.holograms.api.holograms.Hologram;
import hu.rivalsnetwork.rivalssellchest.chests.AbstractChest;
import hu.rivalsnetwork.rivalssellchest.provider.hologram.HologramProvider;
import org.bukkit.Location;

import java.util.HashMap;
import java.util.List;

public class DecentHologramsProvider implements HologramProvider {
    private static final HashMap<AbstractChest, Hologram> map = new HashMap<>();

    @Override
    public HashMap<AbstractChest, Hologram> map() {
        return map;
    }

    @Override
    public Hologram createHologram(String name, Location location, List<String> lines) {
        return DHAPI.createHologram(name, location, lines);
    }

    @Override
    public void removeHologram(Hologram hologram) {
        hologram.delete();
    }
}
