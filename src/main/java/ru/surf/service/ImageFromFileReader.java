package ru.surf.service;

import java.awt.Image;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

public class ImageFromFileReader {
    private final static Loggger logger = Loggger.getInstance();

    @SuppressWarnings("finally")
    public static Image readImageFromFile(String filePath) {
        Image image = null;
        
        try {
            InputStream is = ImageFromFileReader.class.getResourceAsStream(filePath);
            if (is == null) {
                throw new FileNotFoundException();
            }
            image = ImageIO.read(is);
        } catch (FileNotFoundException e) {
            logger.logError("Не удалось найти файл " + filePath);
        } catch (IOException e) {
            logger.logError("Не удалось загрузить изображение из файла " + filePath + " | " + e.getMessage());
        } finally {
            return image;
        }
    }
}
