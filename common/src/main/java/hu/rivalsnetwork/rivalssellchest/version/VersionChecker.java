package hu.rivalsnetwork.rivalssellchest.version;

import hu.rivalsnetwork.rivalssellchest.RivalsSellChestPlugin;

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
}
