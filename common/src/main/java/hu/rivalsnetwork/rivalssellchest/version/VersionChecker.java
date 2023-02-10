package hu.rivalsnetwork.rivalssellchest.version;

import hu.rivalsnetwork.rivalssellchest.RivalsSellChestPlugin;
import hu.rivalsnetwork.rivalssellchest.util.MessageUtil;
import org.bukkit.Bukkit;

public class VersionChecker {
    private static final String[] SUPPORTED_VERSIONS = new String[]{"v1_19_R2"};
    private static boolean isVersionSupported = false;
    private static String serverVersion;

    public static String getServerVersion() {
        return serverVersion;
    }

    public static boolean isServerVersionSupported() {
        return isVersionSupported;
    }

    public void initialize() {
        serverVersion = RivalsSellChestPlugin.getInstance().getServer().getClass().getPackage().getName().split("\\.")[3];

        for (String version : SUPPORTED_VERSIONS) {
            if (version.contains(serverVersion)) {
                isVersionSupported = true;
                break;
            }
        }
    }

    public void verifyVersionSupport() {
        initialize();

        if (!isVersionSupported) {
            MessageUtil.logWarning("The server version you are using is not yet supported!");
            MessageUtil.logWarning("Please check if your server version is supported.");
            MessageUtil.logWarning("If not, and this is a new version, please look out for an update!");
            MessageUtil.logWarning("Disabling!");

            Bukkit.getPluginManager().disablePlugin(RivalsSellChestPlugin.getInstance());
        }
    }
}
