package hu.rivalsnetwork.rivalssellchest.config;

import com.google.common.base.Preconditions;
import dev.dejvokep.boostedyaml.YamlDocument;
import dev.dejvokep.boostedyaml.dvs.versioning.BasicVersioning;
import dev.dejvokep.boostedyaml.libs.org.snakeyaml.engine.v2.common.FlowStyle;
import dev.dejvokep.boostedyaml.libs.org.snakeyaml.engine.v2.common.ScalarStyle;
import dev.dejvokep.boostedyaml.settings.dumper.DumperSettings;
import dev.dejvokep.boostedyaml.settings.general.GeneralSettings;
import dev.dejvokep.boostedyaml.settings.loader.LoaderSettings;
import dev.dejvokep.boostedyaml.settings.updater.UpdaterSettings;
import hu.rivalsnetwork.rivalssellchest.RivalsSellChestPlugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class Config extends AbstractConfig {
    private static YamlDocument config;

    private static final InputStream defaults = RivalsSellChestPlugin.getInstance().getResource("config.yml");

    public static YamlDocument getConfig() {
        return config;
    }

    @Override
    public void initialize() {
        try {
            Preconditions.checkNotNull(defaults, "Could not find config.yml in plugin's resources!");
            config = YamlDocument.create(new File(RivalsSellChestPlugin.getInstance().getDataFolder(), "config.yml"), defaults, GeneralSettings.DEFAULT, LoaderSettings.builder().setAutoUpdate(true).build(), DumperSettings.builder().setFlowStyle(FlowStyle.FLOW).setIndentation(4).setScalarStyle(ScalarStyle.DOUBLE_QUOTED).setMultilineStyle(true).build(), UpdaterSettings.builder().setVersioning(new BasicVersioning("config-version")).build());
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
