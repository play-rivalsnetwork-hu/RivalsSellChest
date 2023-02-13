package hu.rivalsnetwork.rivalssellchest.provider.hologram;

import hu.rivalsnetwork.rivalssellchest.config.Config;
import hu.rivalsnetwork.rivalssellchest.provider.AbstractProviderLoader;
import hu.rivalsnetwork.rivalssellchest.provider.hologram.impl.DecentHologramsProvider;
import org.bukkit.Bukkit;

import java.util.Locale;

public class HologramProviderLoader extends AbstractProviderLoader {
    private static HologramProvider provider;

    @Override
    public void enableProvider() {
        if (Config.getConfig().getString("hooks.hologram").toLowerCase(Locale.ENGLISH).equals("decentholograms")) {
            if (Bukkit.getPluginManager().isPluginEnabled("DecentHolograms"))
                provider = new DecentHologramsProvider();
        } else {
            provider = null;
        }
    }

    public static HologramProvider getProvider() {
        return provider;
    }
}
