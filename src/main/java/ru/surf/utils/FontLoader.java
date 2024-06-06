package ru.surf.utils;

import java.awt.Font;
import java.io.IOException;
import java.io.InputStream;
import java.awt.FontFormatException;

public class FontLoader {
    private static final Loggger logger = Loggger.getInstance();
    public static Font loadFont(String fontFileName, float size, String style) {
        int fontStyle = 0;

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
            // FIXME: Переделать пути на универсальные, не зависящие от ОС
            InputStream inputStream = FontLoader.class.getResourceAsStream("/fonts/" + fontFileName);
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, inputStream).deriveFont(fontStyle, size);
            logger.logInfo("Шрифт " + customFont.getFontName() + " успешно загружен.");
            inputStream.close();
            return customFont;
        } catch (IOException | FontFormatException e) {
            System.err.println("Ошибка при загрузке шрифта: " + e.getMessage());
            // Загрузка дефолтного шрифта в случае ошибки
            return new Font("Arial", fontStyle, (int) size);
        }
    }
}
