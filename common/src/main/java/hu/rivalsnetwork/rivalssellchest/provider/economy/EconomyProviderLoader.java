package hu.rivalsnetwork.rivalssellchest.provider.economy;

import hu.rivalsnetwork.rivalssellchest.config.Config;
import hu.rivalsnetwork.rivalssellchest.provider.AbstractProviderLoader;
import hu.rivalsnetwork.rivalssellchest.provider.economy.impl.VaultEconomyProvider;
import org.bukkit.Bukkit;

import java.util.Locale;

public class EconomyProviderLoader extends AbstractProviderLoader {
    private static EconomyProvider provider;

    public static EconomyProvider getProvider() {
        return provider;
    }

    @Override
    public void enableProvider() {
        if (Config.getConfig().getString("hooks.economy").toLowerCase(Locale.ENGLISH).equals("vault")) {
            if (Bukkit.getPluginManager().isPluginEnabled("Vault"))
                provider = new VaultEconomyProvider();
        } else {
            provider = null;
        }
    }
}
