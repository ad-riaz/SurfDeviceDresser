package ru.surf.service;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

import javax.imageio.ImageIO;

import ru.surf.model.Img;

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

    public static String getDefaultfilePath(String filename) {
        ClassLoader cl = ImageFromFileReader.class.getClassLoader();
        String defaultPath = URLDecoder.decode(
            cl.getResource("img/" + filename)
            .toString(),
            StandardCharsets.UTF_8
        );

        if (
            defaultPath.contains("jar:")
        ) {
            defaultPath = "src/main/resources/img/" + filename;
        } else {
            defaultPath = defaultPath.replace("file:", "");
        }
        return defaultPath;
    }
}
