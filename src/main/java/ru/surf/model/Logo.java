package ru.surf.model;

import java.io.InputStream;

import ru.surf.service.Loggger;

public class Logo extends Img {
    private final Loggger logger = Loggger.getInstance();

    private static final InputStream is = Logo.class.getResourceAsStream("/img/logo.png");
    private final static double dimensionsRatio = 1.4;
    private final static int imageWidthPercentage = 90;
    private final static int yPos = 100;

    public Logo(int sceneWidth) {
        super(
            sceneWidth * imageWidthPercentage / 100, 
            (int) Math.round((double) (sceneWidth * imageWidthPercentage / 100 / dimensionsRatio)), 
            (sceneWidth - sceneWidth * imageWidthPercentage / 100) / 2, 
            yPos, 
            is);
            System.out.println("sceneWidth in logo: " + sceneWidth);
        logger.logInfo("Объект Logo был успешно создан");
    }

    public double getDimensionsRatio() {
        return dimensionsRatio;
    }

    public int getWidthPercentage() {
        return imageWidthPercentage;
    }    

    public int getYPos() {
        return yPos;
    }

    @Override
    public String toString() {
        return "Logo:\n" + 
        "width: " + this.width + "\n" +
        "Height: " + this.height + "\n" +
        "xPos: " + xPos + "\n" +
        "yPos: " + yPos + "\n" +
        "percentage: " + imageWidthPercentage;
    }
}
