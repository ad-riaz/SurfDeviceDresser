package ru.surf.service;

import java.awt.Font;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.awt.FontFormatException;

public class FontLoader {
    private static final Loggger logger = Loggger.getInstance();
    
    @SuppressWarnings("finally")
    public static Font loadFont(String fontFilePath, int size, String style) {
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
}
