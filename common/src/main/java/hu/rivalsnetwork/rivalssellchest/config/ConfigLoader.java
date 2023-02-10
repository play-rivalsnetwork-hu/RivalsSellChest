package hu.rivalsnetwork.rivalssellchest.config;

import java.util.ArrayList;
import java.util.List;

public class ConfigLoader {
    private static final List<AbstractConfig> configs = new ArrayList<>(List.of(new Config()));

    public void loadConfigs() {
        configs.forEach(AbstractConfig::initialize);
    }
}
