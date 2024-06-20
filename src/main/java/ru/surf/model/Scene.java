package ru.surf.model;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import ru.surf.service.AppPropertiesReader;
import ru.surf.service.ColorCreator;
import ru.surf.service.FontService;
import ru.surf.service.Loggger;
import ru.surf.utils.*;

public class Scene {
    private final Loggger logger = Loggger.getInstance();
    
    // Параметры сцены
    private int     width;
    private int     height;
    private int     defaultFontSize;
    private int     lineGap;
    private int     defaultLineGap = 75;
    private Color   textColor;
    private Font    font;
    private File    outputFile; 

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
    
    // FIXME: Проверить и переделать логику рисования текста (см. отступы, размер шрифтв)
    public void create() {
        // Определяем параметры устройства и создаем сцену
        deviceName = device.getVendor().toString() + " " + device.getDeviceName();
        os = device.getOsType() + " " + device.getOsVersion();
        screen = device.getDiagonal() + "  " + device.getScreenWidth() + "x" + device.getScreenHeight();

        // Определяем параметры сцены
        width = device.getScreenWidth();
        height = device.getScreenHeight();
        defaultFontSize = FontService.defineDefaultFontSize(width);
        font = FontService.setEnvironmentFont(defaultFontSize);
        textColor = ColorCreator.create(
            AppPropertiesReader.getInstance().readProperty("fontColor", "255,255,255")
        );
        lineGap = AppPropertiesReader.getInstance().readIntegerValue("gapBetweenLines", defaultLineGap);
        outputFile = new File(deviceName.replace(" ", "_") + "_" + 
                    os.replace(" ", "_") + ".png");

        // Определяем используемые фон и логотип
        defineImages(width, height);

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

        g.dispose();
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
            backgroundImg.getxPosOnCanvas(), 
            backgroundImg.getyPosOnCanvas(), 
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
            logoImg.getxPosOnCanvas(), 
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
        if ((width - getStringWidth(string)) >= 100) {
            int deviceNameStringX = (width - getStringWidth(string)) / 2;
            g.drawString(string, deviceNameStringX, yPos);
            return (getStringHeight() + lineGap);
        } else {
            String[] lines = Utilities.splitLongString(string).split("\n");
            int semigap = lineGap / 4;
            for (int i = 0; i < 2; i++) {
                int x = (width - getStringWidth(lines[i])) / 2;
                g.drawString(lines[i], x, yPos + getStringHeight() * i + semigap * i);
            }
            return (getStringHeight() * 2 + semigap + lineGap);
        }
    }
}
