package hu.rivalsnetwork.rivalssellchest.config;

import dev.dejvokep.boostedyaml.YamlDocument;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConfigLoader {
    private static final List<AbstractConfig> configs = new ArrayList<>(Arrays.asList(new Config(), new Messages(), new UserMadeConfig(), new PricesConfig()));

    @NotNull
    public static YamlDocument create(@NotNull File file) {
        try {
            return YamlDocument.create(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadConfigs() {
        configs.forEach(AbstractConfig::initialize);
    }
}
