package hu.rivalsnetwork.rivalssellchest.util;

import hu.rivalsnetwork.rivalssellchest.chests.PlacedChest;
import net.kyori.adventure.text.minimessage.MiniMessage;

import java.util.ArrayList;
import java.util.List;

public class StringUtils {

    public static List<String> replaceInLines(List<String> lines, PlacedChest chest) {
        List<String> returnLines = new ArrayList<>();

        for (String line : lines) {
            returnLines.add(formatToString(line.replace("<owner>", chest.ownerName())
                    .replace("<money>", String.valueOf(chest.money()))
                    .replace("<items>", String.valueOf(chest.itemsSold()))));
        }

        return returnLines;
    }

    public static String formatToString(String line) {
        return MiniMessage.miniMessage().serialize(MiniMessage.miniMessage().deserialize(line));
    }
 }
