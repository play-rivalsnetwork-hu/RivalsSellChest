package hu.rivalsnetwork.rivalssellchest.config;

import com.google.common.base.Preconditions;
import dev.dejvokep.boostedyaml.YamlDocument;
import hu.rivalsnetwork.rivalssellchest.RivalsSellChestPlugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class PricesConfig extends AbstractConfig {
    private static final InputStream defaults = RivalsSellChestPlugin.getInstance().getResource("prices.yml");
    private static YamlDocument config;

    public static YamlDocument getConfig() {
        return config;
    }

    @Override
    public void initialize() {
        try {
            Preconditions.checkNotNull(defaults, "Could not find prices.yml in plugin's resources!");
            config = YamlDocument.create(new File(RivalsSellChestPlugin.getInstance().getDataFolder(), "prices.yml"));
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
