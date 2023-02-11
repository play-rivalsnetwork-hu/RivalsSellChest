package hu.rivalsnetwork.rivalssellchest.nms;

import org.bukkit.Chunk;
import org.bukkit.entity.Entity;

import java.util.List;

public interface NMSHandler {

    List<Entity> getEntities(Chunk chunk);
}
