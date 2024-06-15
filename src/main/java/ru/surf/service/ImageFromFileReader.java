package ru.surf.service;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

public class ImageFromFileReader {
    private final static Loggger logger = Loggger.getInstance();

    @SuppressWarnings("finally")
    public static Image readImageFromFile(String filePath) {
        Image image = new BufferedImage(1, 1, 1);
        InputStream is = null;
        
        try {
            is = new FileInputStream(filePath);
            image = ImageIO.read(is);
        } catch (FileNotFoundException e) {
            logger.logError("Не удалось найти файл " + filePath + ". Проверь правильность пути до файла. " + e.getMessage());
        } catch (IOException e) {
            logger.logError("Не удалось загрузить изображение из файла " + filePath + " | " + e.getMessage());
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    logger.logError("Не удалось закрыть InputStream при чтении изображения из файла. " + e.getMessage());
                }
            }
            return image;
        }
    }
}
