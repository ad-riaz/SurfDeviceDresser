package ru.surf.service;

import java.awt.Font;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;

public class FontService {
    private static final Loggger logger = Loggger.getInstance();

    public static Font setEnvironmentFont(int defaultFontSize) {
        GraphicsEnvironment ge = null;
        String fontFilePath = AppPropertiesReader
                                .getInstance()
                                .readProperty(
                                    "font",
                                    getDefaultFontFilePath("default-font.ttf")
                                );
        
        ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Font defaultFont = loadFontFromFile(fontFilePath, defaultFontSize, "bold");
        ge.registerFont(defaultFont);
        return defaultFont;            
    }

    public static int defineDefaultFontSize(int screenWidth) {
        int fontSize = 80;
        float fontSizeFactor = 1.3f;
        String stringFontSize = AppPropertiesReader.getInstance().readProperty("fontSize", "70");
        String autoFontSizingEnabled = AppPropertiesReader.getInstance().readProperty("autoFontSizingEnabled", "false");

        try {
            int customFontSize = Integer.parseInt(stringFontSize);
            if (customFontSize > 0) fontSize = customFontSize;
        } catch(NumberFormatException e) {
            logger.logWarning("Произошла ошибка при чтении размера шрифта из файла конфигурации.\n" +
            "Размер шрифта не может быть равен " + stringFontSize + "\nБыл использован размер шрифта " + fontSize);
        }

        if (autoFontSizingEnabled.equals("true")) {
            if (screenWidth <= 830) {
                fontSize /= fontSizeFactor;
            } else if (screenWidth > 1440) {
                fontSize *= fontSizeFactor;
            }
        }       

        return fontSize;
    }
    
    @SuppressWarnings("finally")
    private static Font loadFontFromFile(String fontFilePath, int size, String style) {
        int fontStyle = 0;
        Font customFont = new Font("Arial", fontStyle, size);
        InputStream inputStream = null;

        switch (style.toUpperCase()) {
            case "PLAIN":
                fontStyle = Font.PLAIN;
                break;
            case "BOLD":
                fontStyle = Font.BOLD;
                break;
            case "ITALIC":
                fontStyle = Font.ITALIC;
                break;
            default:
                break;
        }

        try {
            inputStream = new FileInputStream(fontFilePath);
            customFont = Font.createFont(Font.TRUETYPE_FONT, inputStream).deriveFont(fontStyle, size);
            logger.logInfo("Шрифт " + customFont.getFontName() + " успешно загружен.");
        } catch(FileNotFoundException e) {
            logger.logError("Не удалось загрузить файл шрифта по пути " + fontFilePath);
        } catch(IOException e) {
            logger.logError("Не удалось создать шрифт из файла. " + e.getMessage());
        } catch (FontFormatException e) {
            System.err.println("The specified font is bad. " + e.getMessage());
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch(IOException e) {
                    logger.logError("Не удалось закрыть InputStream после чтения шрифта из файла. " + e.getMessage());
                }
            }
            if (customFont.getFontName().equals("Arial")) {
                logger.logInfo("В качестве шрифта был использован стандартный Arial");
            }
            
            return customFont;
        }
    }

    private static String getDefaultFontFilePath(String filename) {
        ClassLoader cl = ImageFromFileReader.class.getClassLoader();
        String defaultPath = URLDecoder.decode(
            cl.getResource("fonts/" + filename)
            .toString(),
            StandardCharsets.UTF_8
        );

        if (
            defaultPath.contains("jar:")
        ) {
            defaultPath = "src/main/resources/fonts/" + filename;
        } else {
            defaultPath = defaultPath.replace("file:", "");
        }
        return defaultPath;
    }
}
