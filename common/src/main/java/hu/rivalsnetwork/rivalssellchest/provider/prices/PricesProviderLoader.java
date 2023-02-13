package hu.rivalsnetwork.rivalssellchest.provider.prices;

import hu.rivalsnetwork.rivalssellchest.config.Config;
import hu.rivalsnetwork.rivalssellchest.provider.prices.impl.LocalPricesProvider;
import hu.rivalsnetwork.rivalssellchest.provider.prices.impl.ShopGUIPlusPricesProvider;
import org.bukkit.Bukkit;

import java.util.Locale;

public class PricesProviderLoader {
    private static PricesProvider provider;

    public void enableProvider() {
        switch (Config.getConfig().getString("hooks.price").toLowerCase(Locale.ENGLISH)) {
            case "shopguiplus" -> {
                if (Bukkit.getPluginManager().isPluginEnabled("ShopGUIPlus"))
                    provider = new ShopGUIPlusPricesProvider();
                else provider = new LocalPricesProvider();
            }
            case "local" -> provider = new LocalPricesProvider();
            default -> new LocalPricesProvider();
        }
    }

    public static PricesProvider getProvider() {
        return provider;
    }
}

