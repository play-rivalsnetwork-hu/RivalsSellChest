package hu.rivalsnetwork.rivalssellchest.provider.economy.impl;

import hu.rivalsnetwork.rivalssellchest.provider.economy.EconomyProvider;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.RegisteredServiceProvider;

public class VaultEconomyProvider implements EconomyProvider  {
    private static Economy economy = null;

    public VaultEconomyProvider() {
        RegisteredServiceProvider<Economy> provider = Bukkit.getServer().getServicesManager().getRegistration(Economy.class);
        if (provider == null) {
            return;
        }
        economy = provider.getProvider();
    }

    @Override
    public double getBalance(OfflinePlayer offlinePlayer) {
        return economy.getBalance(offlinePlayer);
    }

    @Override
    public void takeBalance(OfflinePlayer offlinePlayer, double amount) {
        economy.withdrawPlayer(offlinePlayer, amount);
    }

    @Override
    public void giveBalance(OfflinePlayer offlinePlayer, double amount) {
        economy.depositPlayer(offlinePlayer, amount);
    }
}
