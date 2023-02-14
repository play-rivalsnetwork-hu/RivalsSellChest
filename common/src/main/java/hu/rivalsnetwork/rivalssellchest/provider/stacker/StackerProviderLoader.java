package hu.rivalsnetwork.rivalssellchest.provider.stacker;

import hu.rivalsnetwork.rivalssellchest.config.Config;
import hu.rivalsnetwork.rivalssellchest.provider.AbstractProviderLoader;
import hu.rivalsnetwork.rivalssellchest.provider.stacker.impl.DefaultStackerProvider;
import hu.rivalsnetwork.rivalssellchest.provider.stacker.impl.RoseStackerStackerProvider;
import hu.rivalsnetwork.rivalssellchest.provider.stacker.impl.WildStackerStackerProvider;
import org.bukkit.Bukkit;

import java.util.Locale;

public class StackerProviderLoader extends AbstractProviderLoader {
    private static StackerProvider provider;

    public static StackerProvider provider() {
        return provider;
    }

    @Override
    public void enableProvider() {
        switch (Config.getConfig().getString("hooks.stacker").toLowerCase(Locale.ENGLISH)) {
            case "wildstacker" -> {
                if (Bukkit.getPluginManager().isPluginEnabled("WildStacker"))
                    provider = new WildStackerStackerProvider();
            }
            case "rosestacker" -> {
                if (Bukkit.getPluginManager().isPluginEnabled("RoseStacker"))
                    provider = new RoseStackerStackerProvider();
            }
            default -> provider = new DefaultStackerProvider();
        }
    }
}
