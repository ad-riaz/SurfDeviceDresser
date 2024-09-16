package ru.surf.model;

import ru.surf.service.Loggger;
import ru.surf.service.AppPropertiesReader;
import ru.surf.service.ImageTransformer;
import ru.surf.service.ImageFromFileReader;

public class BackgroundImg extends Img {
    private final Loggger logger = Loggger.getInstance();
    private static SceneProperties sceneProperties = SceneProperties.getInstance();
    private static String filePath = sceneProperties.getBackgroundPath();

    public BackgroundImg(int screenWidth, int screenHeight, int xPosOnCanvas, int yPosOnCanvas) {
        super(screenWidth, screenHeight, xPosOnCanvas, yPosOnCanvas, filePath);
        this.image = ImageTransformer.scaleAndCropImage(screenWidth, screenHeight, this.getImage());
    }
}
