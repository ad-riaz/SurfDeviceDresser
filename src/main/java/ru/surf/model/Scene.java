package ru.surf.model;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import ru.surf.service.FontLoader;
import ru.surf.service.Loggger;
import ru.surf.utils.*;

public class Scene {
    private final Loggger logger = Loggger.getInstance();
    
    // Параметры сцены
    public  Color   textColor;
    private int     width;
    private int     height;
    private File outputFile; 
    private int     defaultFontSize;
    private int     lineHeight;
    private Font    font;

    // Параметры устройства
    private Device device;
    private String deviceName;     
    private String os;         
    private String screen;
    
    // фоновое изображение и логотип
    private BackgroundImg   backgroundImg;
    private Logo            logoImg;

    // Создание изображения
    private BufferedImage   image;
    private Graphics2D      g;
    

    public Scene(Device device) {
        this.device = device;
    }
    
    public void create() {
        // Определяем параметры устройства и создаем сцену
        deviceName = device.getVendor().toString() + " " + device.getDeviceName();
        os = device.getOsType() + " " + device.getOsVersion();
        screen = device.getDiagonal() + "  " + device.getScreenWidth() + "x" + device.getScreenHeight();

        // Проверка на класс, если понадобятся классозависимые поля
        // if (device instanceof AndroidDevice) {
        //     AndroidDevice AndroidDevice = (AndroidDevice) device;
        // } else if (device instanceof AppleDevice) {
        //     AppleDevice AppleDevice = (AppleDevice) device;
        // }
        
        // TODO: Задать путь до входных и выходных файлов через application.properties
        // Указать путь в виде строки и распарсить его для использования в Paths.get()

        // Определяем параметры сцены
        width = device.getScreenWidth();
        height = device.getScreenHeight();
        defaultFontSize = defineDefaultFontSize(width);
        font = defineDefaultFont(defaultFontSize);
        textColor = Color.WHITE;
        lineHeight = (int) Math.round(height * 0.02 / 5);
        outputFile = new File(deviceName.replace(" ", "_") + "_" + os.replace(" ", "_") + ".png");

        // Определяем используемые фон и логотип
        defineImages(this.width, this.height);

        // Создаем 2D графику
        createGraphics();
        // Установить цвет и шрифт текста на сцене
        g.setColor(textColor);
        g.setFont(font); 

        // Отрисовываем на сцене фоновое изображение и логотип
        drawBackground(g, backgroundImg);
        drawLogo(g, logoImg);

        // draw text
        int vertMiddle = height / 100 * 65;
        String[] lines = {deviceName, screen, os};
        for (int i = 0; i < lines.length; i++) {
            vertMiddle += drawString(lines[i], vertMiddle);
        }        

        //
        g.dispose();
    }

    private Font defineDefaultFont(int defaultFontSize) {
        GraphicsEnvironment ge = null;

        try {
            ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            Font defaultFont = FontLoader.loadFont("default-font.ttf", defaultFontSize, "bold");
            ge.registerFont(defaultFont);
            return defaultFont;
        } catch (Exception e) {
            logger.logError(e.getMessage());
            // TODO: Подумать, что можно сделать с null
            return null;
        }            
    }

    private int defineDefaultFontSize(int screenWidth) {
        int deviceNamefontSize = 0;

        if (screenWidth <= 830) {
            deviceNamefontSize = 70;
        } else if (screenWidth > 830 && screenWidth <= 1440) {
            deviceNamefontSize = 100;
        } else if (screenWidth > 1440) {
            deviceNamefontSize = 130;
        }

        return deviceNamefontSize;
    }

    public void exportBackground() {
        try {
            ImageIO.write(image, "PNG", outputFile); // Сохранение изображения с текстом
        } catch (Exception e) {
            logger.logError("При сохранении финального изображения в файл произошла ошибка: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void defineImages(int width, int height) {
        backgroundImg = new BackgroundImg(width, height, 0, 0);
        logoImg = new Logo(width);
    }

    private void createGraphics() {
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        g = image.createGraphics();
    }

    private void drawBackground(Graphics2D g, BackgroundImg backgroundImg) {
        // Нарисовать подложку
        g.drawImage(
            backgroundImg.getImage(),
            backgroundImg.getxPos(), 
            backgroundImg.getyPos(), 
            backgroundImg.getWidth(), 
            backgroundImg.getHeight(), 
            null
        );
        logger.logInfo("Фоновое изображение было отрисовано на сцене");
    }

    private void drawLogo(Graphics2D g, Logo logoImg) {
        // Нарисовать логотип    
        g.drawImage(
            logoImg.getImage(), 
            logoImg.getxPos(), 
            logoImg.getYPos(), 
            logoImg.getWidth(), 
            logoImg.getHeight(), 
            null
        );
        logger.logInfo("Логотип был отрисован на сцене");
    }

    private int getStringWidth(String string) {
        return g.getFontMetrics().stringWidth(string);
    }

    private int getStringHeight() {
        return g.getFontMetrics().getHeight();
    }

    private int drawString(String string, int yPos) {
        if ((width - getStringWidth(string)) >= 40) {
            int deviceNameStringX = (width - getStringWidth(string)) / 2;
            g.drawString(string, deviceNameStringX, yPos);
            return (getStringHeight() * 2 + lineHeight);
        } else {
            String[] lines = Utilities.splitLongString(string).split("\n");
            for (int i = 0; i < 2; i++) {
                int x = (width - getStringWidth(lines[i])) / 2;
                g.drawString(lines[i], x, yPos + getStringHeight() * i + lineHeight * i);
            }
            return (getStringHeight() * 2 + lineHeight * 2);
        }
    }
}
