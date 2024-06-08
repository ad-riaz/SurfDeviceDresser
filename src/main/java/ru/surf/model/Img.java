package ru.surf.model;

import java.awt.image.BufferedImage;
import java.io.InputStream;

import javax.imageio.ImageIO;

import ru.surf.service.Loggger;

public abstract class Img {
    private final Loggger logger = Loggger.getInstance();

    protected int width;
    protected int height;
    protected int xPos;
    protected int yPos;
    protected BufferedImage image;

    protected Img(
        int width,
        int height,
        int xPos,
        int yPos,
        InputStream inputStream
    ) {
        this.width = width;
        this.height = height;
        this.xPos = xPos;
        this.yPos = yPos;
        
        try {
            image = ImageIO.read(inputStream);
        } catch (Exception e) {
            logger.logError("Не удалось загрузить изображение из файла | " + e.getMessage());
            image = null;
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getxPos() {
        return xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public BufferedImage getImage() {
        return image;
    }

    @Override
    public String toString() {
        return "Img [width=" + width + ", height=" + height + ", xPos=" + xPos + ", yPos=" + yPos + ", image=" + image
                + "]";
    }    
}
