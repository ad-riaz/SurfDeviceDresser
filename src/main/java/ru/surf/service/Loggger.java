package ru.surf.service;

import java.util.logging.*;

public class Loggger {
    private static final Logger logger = Logger.getLogger(Loggger.class.getName());
    private static Loggger instance;

    public static Loggger getInstance() {
        if (instance == null) {
            instance = new Loggger();
        }
        return instance;
    }

    public static void logInfo(String message) {
        logger.log(Level.INFO, message);
    }

    public static void logWarning(String message) {
        logger.log(Level.WARNING, message);
    }

    public static void logError(String message) {
        logger.log(Level.SEVERE, message);
    }
}