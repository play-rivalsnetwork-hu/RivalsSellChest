package hu.rivalsnetwork.rivalssellchest.util;

import com.google.common.collect.ImmutableList;

import java.util.List;
import java.util.regex.Pattern;

public class ColorUtil {

    private static final List<Pattern> HEX_PATTERNS = new ImmutableList.Builder<Pattern>()
            .add(Pattern.compile("&#([a-fA-F0-9]{6})"))
            .add(Pattern.compile("<#([a-fA-F0-9]{6})>"))
            .add(Pattern.compile("#([a-fA-F0-9]{6})"))
            .build();

}
