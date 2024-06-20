package ru.surf.model;

import ru.surf.service.Loggger;
import ru.surf.service.AppPropertiesReader;
import ru.surf.service.ImageTransformer;
import ru.surf.service.ImageFromFileReader;

public class BackgroundImg extends Img {
    private final Loggger logger = Loggger.getInstance();
    private static String filePath = AppPropertiesReader.getInstance()
        .readProperty(  "backgroundPath", 
                            ImageFromFileReader.getDefaultfilePath("background.jpg"));

    public BackgroundImg(int screenWidth, int screenHeight, int xPosOnCanvas, int yPosOnCanvas) {
        super(screenWidth, screenHeight, xPosOnCanvas, yPosOnCanvas, filePath);
        this.image = ImageTransformer.scaleAndCropImage(screenWidth, screenHeight, this.getImage());
        logger.logInfo("Объект Background был успешно создан");
    }
}
