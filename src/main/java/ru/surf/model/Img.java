package ru.surf.model;

import java.awt.Image;

import ru.surf.service.ImageFromFileReader;

public abstract class Img {
    protected int width;
    protected int height;
    protected int xPosOnCanvas;
    protected int yPosOnCanvas;
    protected Image image;

    protected Img(
        int width,
        int height,
        int xPos,
        int yPos,
        String filePath
    ) {
        this.width = width;
        this.height = height;
        this.xPosOnCanvas = xPos;
        this.yPosOnCanvas = yPos;
        this.image = ImageFromFileReader.readImageFromFile(filePath);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getxPosOnCanvas() {
        return xPosOnCanvas;
    }

    public int getyPosOnCanvas() {
        return yPosOnCanvas;
    }

    public Image getImage() {
        return image;
    }

    @Override
    public String toString() {
        return "Img [width=" + width + ", height=" + height + ", xPos=" + xPosOnCanvas + ", yPos=" + yPosOnCanvas + ", image=" + image
                + "]";
    }
}
