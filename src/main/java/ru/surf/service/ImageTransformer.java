package ru.surf.service;

import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.ReplicateScaleFilter;
import java.awt.Image;
import java.awt.Toolkit;

public class ImageTransformer {
    public static Loggger logger = Loggger.getInstance();

    public static Image scaleAndCropImage(int areaWidth, int areaHeight, Image source) {
        Image emptyImage = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        
        if (source == null) {
            return emptyImage;
        }
        
        Image scaledImage = null;
        int sourceWidth = source.getWidth(null);
        int sourceHeight = source.getHeight(null);;
        int xPos = 0;
        int yPos = 0;
        
        if (sourceWidth > 0 && sourceHeight > 0) {            
            // Если размер области экрана телефона меньше чем размер исходной картинки
            // то просто вырезаем область размером с экран телефона из области по центру
            if (areaWidth <= sourceWidth && areaHeight <= sourceHeight) {
                scaledImage = source;
                xPos = (sourceWidth - areaWidth) / 2;
                yPos = (sourceHeight - areaHeight) / 2;
            // Если размер области экрана телефона больше чем размер исходной картинки
            // то исходную картинку надо растянуть до размеров экрана телефона
            } else if (areaWidth > sourceWidth && areaHeight < sourceHeight) {
                scaledImage = scaleImage(source, areaWidth, sourceHeight);
                xPos = 0;
                yPos = (scaledImage.getHeight(null) - areaHeight) / 2;
            } else if (areaWidth < sourceWidth && areaHeight > sourceHeight) {
                scaledImage = scaleImage(source, sourceWidth, areaHeight);
                xPos = (scaledImage.getWidth(null) - areaWidth) / 2;
                yPos = 0;
            } else if (areaWidth > sourceWidth && areaHeight > sourceHeight) {
                scaledImage = scaleImage(source, areaWidth, areaHeight);
                xPos = 0;
                yPos = 0;
            }
            
            return cropImage(scaledImage, xPos, yPos, areaWidth, areaHeight);
        } else {
            logger.logError("Размеры исходного изображения не могут быть меньше 0");
            return emptyImage;
        }
    } 
    
    public static Image scaleImage(Image source, int width, int height) {
        ReplicateScaleFilter scaleFilter = new ReplicateScaleFilter(width, height);
        FilteredImageSource scaledImageSource = new FilteredImageSource(source.getSource(), scaleFilter);
        return Toolkit.getDefaultToolkit().createImage(scaledImageSource);
    }

    public static Image cropImage(Image source, int xPos, int yPos, int areaWidth, int areaHeight) {
        CropImageFilter cropFilter = new CropImageFilter(xPos, yPos, areaWidth, areaHeight);
        FilteredImageSource croppedImageSource = new FilteredImageSource(source.getSource(), cropFilter);
        return Toolkit.getDefaultToolkit().createImage(croppedImageSource);
    }
}
