package ru.surf.model;

import ru.surf.service.Loggger;
import ru.surf.service.ImageCropper;

public class BackgroundImg extends Img {
    private final Loggger logger = Loggger.getInstance();
    // TODO: Load from application.properties
    private static String filePath = "/img/background.jpg";

    public BackgroundImg(int screenWidth, int screenHeight, int xPosOnCanvas, int yPosOnCanvas) {
        super(screenWidth, screenHeight, xPosOnCanvas, yPosOnCanvas, filePath);
        this.image = ImageCropper.cropImage(screenWidth, screenHeight, this.getImage());
        logger.logInfo("Объект Background был успешно создан");
    }
}
