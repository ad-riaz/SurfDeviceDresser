package ru.surf.model;

import java.io.InputStream;

import javax.imageio.ImageIO;

import ru.surf.utils.Loggger;

public class BackgroundImg extends Img {
    private final Loggger logger = Loggger.getInstance();
    private static final InputStream is = BackgroundImg.class.getResourceAsStream("/img/background.jpg");

    public BackgroundImg(int width, int height, int xPos, int yPos) {
        super(width, height, xPos, yPos, is);
        logger.logInfo("Объект Background был успешно создан");
    }

    // TODO: Crop image
    public void kekw() {
        try {
            image = ImageIO.read(is);
            image.getWidth();
        } catch (Exception e) {
            logger.logError("Не удалось загрузить изображение из файла | " + e.getMessage());
            image = null;
        }
    }
}
