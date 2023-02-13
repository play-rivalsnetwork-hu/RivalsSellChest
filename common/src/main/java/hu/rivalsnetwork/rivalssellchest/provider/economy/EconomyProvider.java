package hu.rivalsnetwork.rivalssellchest.provider.economy;

import org.bukkit.OfflinePlayer;

public interface EconomyProvider {

    double getBalance(OfflinePlayer offlinePlayer);

    void takeBalance(OfflinePlayer offlinePlayer, double amount);

    void giveBalance(OfflinePlayer offlinePlayer, double amount);
}
