package hu.rivalsnetwork.rivalssellchest.provider;

import hu.rivalsnetwork.rivalssellchest.provider.economy.EconomyProviderLoader;
import hu.rivalsnetwork.rivalssellchest.provider.hologram.HologramProviderLoader;
import hu.rivalsnetwork.rivalssellchest.provider.prices.PricesProviderLoader;

import java.util.Arrays;
import java.util.List;

public class ProviderLoader {
    private static final List<AbstractProviderLoader> providerLoaders = Arrays.asList(new EconomyProviderLoader(), new HologramProviderLoader(), new PricesProviderLoader());

    public void loadProviders() {
        providerLoaders.forEach(AbstractProviderLoader::enableProvider);
    }
}
