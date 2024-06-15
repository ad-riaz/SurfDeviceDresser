package ru.surf.model;
import java.awt.Image;

import ru.surf.service.AppPropertiesReader;
import ru.surf.service.ImageFromFileReader;
import ru.surf.service.Loggger;

public class Logo extends Img {
    private static String logoPath = AppPropertiesReader.getInstance().readProperty("logoPath", "./src/main/resources/img/logo.png");
    private static final int logoWidthPercentage = Integer.parseInt(AppPropertiesReader.getInstance().readProperty("logoWidthPercentage", "90")); 
    private static final int yPosOnCanvas = Integer.parseInt(AppPropertiesReader.getInstance().readProperty("yPosOnCanvas", "100"));
    private final Loggger logger = Loggger.getInstance();

    public Logo(int sceneWidth) {
        super(
            sceneWidth * logoWidthPercentage / 100, 
            (int) Math.round((double) (sceneWidth * logoWidthPercentage / 100 / calcDimensionsRatio())), 
            (sceneWidth - sceneWidth * logoWidthPercentage / 100) / 2, 
            yPosOnCanvas, 
            logoPath);
        logger.logInfo("Объект Logo был успешно создан");
    }

    private static double calcDimensionsRatio() {
        Image logo = ImageFromFileReader.readImageFromFile(logoPath);
        return Math.round((double) logo.getWidth(null) / logo.getHeight(null) * 100.00) / 100.00;
    }

    public int getWidthPercentage() {
        return logoWidthPercentage;
    }    

    public int getYPos() {
        return yPosOnCanvas;
    }

    @Override
    public String toString() {
        return "Logo:\n" + 
        "width: " + this.width + "\n" +
        "Height: " + this.height + "\n" +
        "xPos: " + xPosOnCanvas + "\n" +
        "yPos: " + yPosOnCanvas + "\n" +
        "percentage: " + logoWidthPercentage;
    }
}
