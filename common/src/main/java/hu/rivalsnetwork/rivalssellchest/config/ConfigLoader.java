package hu.rivalsnetwork.rivalssellchest.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConfigLoader {
    private static final List<AbstractConfig> configs = new ArrayList<>(Arrays.asList(new Config(), new Messages(), new UserMadeConfig()));

    public void loadConfigs() {
        configs.forEach(AbstractConfig::initialize);
    }
}
