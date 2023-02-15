package hu.rivalsnetwork.rivalssellchest.provider.hologram.impl;

import eu.decentsoftware.holograms.api.DHAPI;
import eu.decentsoftware.holograms.api.holograms.Hologram;
import hu.rivalsnetwork.rivalssellchest.chests.AbstractChest;
import hu.rivalsnetwork.rivalssellchest.provider.hologram.HologramProvider;
import org.bukkit.Location;

import java.util.HashMap;
import java.util.List;

public class DecentHologramsProvider implements HologramProvider {

    @Override
    public Hologram createHologram(String name, Location location, List<String> lines) {
        Hologram hologram = DHAPI.createHologram(name, location, lines);
        hologram.showAll();
        return hologram;
    }

    @Override
    public void removeHologram(Hologram hologram) {
        hologram.delete();
    }
}
