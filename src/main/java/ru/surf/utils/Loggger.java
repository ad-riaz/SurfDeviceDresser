package ru.surf.utils;

import java.util.logging.*;

public class Loggger {
    private Logger logger;
    private ConsoleHandler consoleHandler;
    
    private Loggger() {
        logger = Logger.getLogger("Loggger");
        consoleHandler = new ConsoleHandler();
        
        try {
            consoleHandler.setEncoding("UTF-8");
            logger.addHandler(consoleHandler);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Loggger instance = new Loggger();

    public static Loggger getInstance() {
        return instance;
    }

    public void logInfo(String message) {
        logger.log(Level.INFO, message);
    }

    public void logError(String message) {
        logger.log(Level.SEVERE, message);
    }    
}

// package ru.surf.utils;

// import java.util.logging.*;

// public class Loggger {
//     private static Logger logger = Logger.getLogger("Loggger");
//     private static Loggger instance;
//     private ConsoleHandler consoleHandler;
    
//     private Loggger() {
//         consoleHandler =  new ConsoleHandler();
        
//         try {
//             consoleHandler.setEncoding("UTF-8");
//             logger.addHandler(consoleHandler);
//         } catch (Exception e) {
//             e.printStackTrace();
//         }
//     }

//     public static Loggger getInstance() {
//         if (instance == null) {
//             instance = new Loggger();
//         }
//         return instance;
//     }

//     public static void logInfo(String message) {
//         logger.log(Level.INFO, message);
//     }

//     public static void logError(String message) {
//         logger.log(Level.SEVERE, message);
//     }    
// }