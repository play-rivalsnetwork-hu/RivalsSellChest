package hu.rivalsnetwork.rivalssellchest.provider;

import hu.rivalsnetwork.rivalssellchest.provider.economy.EconomyProviderLoader;
import hu.rivalsnetwork.rivalssellchest.provider.hologram.HologramProviderLoader;
import hu.rivalsnetwork.rivalssellchest.provider.prices.PricesProviderLoader;
import hu.rivalsnetwork.rivalssellchest.provider.stacker.StackerProviderLoader;

import java.util.Arrays;
import java.util.List;

public class ProviderLoader {
    private static final List<AbstractProviderLoader> providerLoaders = Arrays.asList(new EconomyProviderLoader(), new HologramProviderLoader(), new PricesProviderLoader(), new StackerProviderLoader());

    public void loadProviders() {
        providerLoaders.forEach(AbstractProviderLoader::enableProvider);
    }
}
