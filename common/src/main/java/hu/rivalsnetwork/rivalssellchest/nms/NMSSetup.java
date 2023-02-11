package hu.rivalsnetwork.rivalssellchest.nms;

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

        try {
            handler = (NMSHandler) Class.forName("hu.rivalsnetwork.rivalssellchest.nms." + VersionChecker.getServerVersion() + ".NMSHandler").getConstructor().newInstance();
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException exception) {
            throw new RuntimeException(exception);
        }
    }
}
