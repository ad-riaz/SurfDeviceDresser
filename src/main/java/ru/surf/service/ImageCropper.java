package ru.surf.service;

import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.Image;
import java.awt.Toolkit;

public class ImageCropper {
    public static Loggger logger = Loggger.getInstance();

    public static Image cropImage(int areaWidth, int areaHeight, Image source) {
        Image emptyImage = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        
        if (source == null) {
            return emptyImage;
        }
        
        Image croppedImage;
        CropImageFilter cropFilter = null;
        FilteredImageSource filteredIS;
        int sourceWidth = source.getWidth(null);
        int sourceHeight = source.getHeight(null);;
        int xPos;
        int yPos;
        
        if (sourceWidth > 0 && sourceHeight > 0) {            
            // Если размер области экрана телефона меньше чем размер исходной картинки
            // то просто вырезаем область размером с экран телефона из области по центру
            if (sourceWidth >= areaWidth && sourceHeight >= areaHeight) {
                xPos = (sourceWidth - areaWidth) / 2;
                yPos = (sourceHeight - areaHeight) / 2;
                cropFilter = new CropImageFilter(xPos, yPos, areaWidth, areaHeight);
            // Если размер области экрана телефона больше чем размер исходной картинки
            // то исходную картинку надо растянуть до размеров экрана телефона, ?отцентрировав ее?
            } else if (areaWidth > sourceWidth) {
                xPos = 0;
                yPos = (sourceHeight - areaHeight) / 2;
                cropFilter = new CropImageFilter(xPos, yPos, areaWidth, areaHeight);
            } else if (areaHeight > sourceHeight) {
                xPos = (sourceWidth - areaWidth) / 2;
                yPos = 0;
                cropFilter = new CropImageFilter(xPos, yPos, areaWidth, areaHeight);
            } else if (areaWidth > sourceWidth && areaHeight > sourceHeight) {
                xPos = 0;
                yPos = 0;
                cropFilter = new CropImageFilter(xPos, yPos, areaWidth, areaHeight);
            }
            
            filteredIS = new FilteredImageSource(source.getSource(), cropFilter);
            croppedImage = Toolkit.getDefaultToolkit().createImage(filteredIS);
            return croppedImage;
        } else {
            logger.logError("Размеры исходного изображения не могут быть меньше 0");
            return emptyImage;
        }
    }   
}
