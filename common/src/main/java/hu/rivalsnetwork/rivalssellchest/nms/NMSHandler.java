package hu.rivalsnetwork.rivalssellchest.nms;

import org.bukkit.Location;
import org.bukkit.entity.Entity;

import java.util.List;

public interface NMSHandler {

    List<Entity> getEntities(Location loc);
}
