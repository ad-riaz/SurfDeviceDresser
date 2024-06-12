package ru.surf.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AppPropertiesReader {
    private static Loggger logger = Loggger.getInstance();
    private static AppPropertiesReader instance;
    private static String propsFilePath = "application.properties";
    private static Properties props = new Properties();
    private InputStream is;

    private AppPropertiesReader() {
        try {
            is = new FileInputStream(propsFilePath);
            props.load(is);
        } catch (FileNotFoundException e) {
            logger.logError("Файл с настройками приложения не найден по пути " + propsFilePath);
        } catch (IOException e) {
            logger.logError("Произошла ошибка чтения настроек приложения из InputStream");
        } catch(IllegalArgumentException e) {
            logger.logError("Input stream contains a malformed Unicode escape sequence");
        }
    }

    public static AppPropertiesReader getInstance() {
        if (instance == null) {
            instance = new AppPropertiesReader();
        }
        return instance;
    }

    public String readProperty(String prop, String defaultValue) {
        String value = props.getProperty(prop);
        if (value.isBlank() || value.equals(null)) {
            logger.logError("Не удалось прочитать значение " + prop + 
            " в файле " + propsFilePath + "\n" +
            "Было использовано значение по умолчанию: " + defaultValue);
            return defaultValue;
        }
        return value;
    } 

    public void close() {
        try {
            is.close();
        } catch (IOException e) {
            logger.logError("Не получилось закрыть InputStream в AppPropertiesReader \n" + e.getMessage());
        }
    }
}
