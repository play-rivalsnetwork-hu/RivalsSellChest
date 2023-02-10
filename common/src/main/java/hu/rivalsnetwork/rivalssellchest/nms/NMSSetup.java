package hu.rivalsnetwork.rivalssellchest.nms;

import hu.rivalsnetwork.rivalssellchest.util.MessageUtil;
import hu.rivalsnetwork.rivalssellchest.version.VersionChecker;

import java.lang.reflect.InvocationTargetException;

public class NMSSetup {
    private static NMSHandler handler;

    public static NMSHandler getHandler() {
        if (handler == null) setup();
        return handler;
    }

    public static void setup() {
        if (handler != null) return;
        if (!VersionChecker.isServerVersionSupported()) {
            MessageUtil.logWarning("The server version you are using is not yet supported!");
            MessageUtil.logWarning("Please check if your server version is supported.");
            MessageUtil.logWarning("If not, and this is a newer version, please look out for an update!");
            return;
        }

        try {
            handler = (NMSHandler) Class.forName("hu.rivalsnetwork.rivalssellchest." + VersionChecker.getServerVersion() + ".NMSHandler").getConstructor().newInstance();
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException exception) {
            throw new RuntimeException(exception);
        }
    }
}
