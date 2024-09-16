package ru.surf.model;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

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
    private Color   textColor;
    private Font    font = FontService.setEnvironmentFont(80);
    private File    outputFile;
    private String  autoFontSizingEnabled;

    // Параметры устройства
    private Device device;
    private String deviceName;
    private String shortDeviceName;
    private String os;         
    private String screen;
    
    // фоновое изображение и логотип
    private BackgroundImg   backgroundImg;
    private Logo            logoImg;

    // Создание изображения
    private BufferedImage   image;
    private Graphics2D      g;

    private SceneProperties sceneProperties = SceneProperties.getInstance();
    private File outDirectory;
    

    public Scene() {
        String userHome = System.getProperty("user.home");
        String directoryPath = userHome + File.separator + "desktop" + File.separator + "SurfDeviceDresser";
        outDirectory = new File(directoryPath);
        if (!outDirectory.exists()) {
            outDirectory.mkdirs();
        }
    }
    
    public void init(Device device) {
        this.device = device;

        // Определяем параметры устройства и создаем сцену
        deviceName = device.getVendor().toString() + " " + device.getDeviceName();
        shortDeviceName = device.getShortDeviceName();
        os = device.getOsType() + " " + device.getOsVersion();
        screen = device.getDiagonal() + "  " + device.getScreenWidth() + "x" + device.getScreenHeight();

        // Определяем параметры сцены
        outputFile = new File(
                outDirectory,
                deviceName.replace(" ", "_") + "_" +
                        os.replace(" ", "_") + ".png");
        width = device.getScreenWidth();
        height = device.getScreenHeight();
        // Определяем параметры текста
        defaultFontSize = FontService.defineDefaultFontSize(width);
        font = font.deriveFont((float) defaultFontSize);
        textColor = ColorCreator.create(sceneProperties.getFontColor());

        // Определить отступ между строками
        lineGap = sceneProperties.getGapBetweenLines();
        autoFontSizingEnabled = sceneProperties.getAutoFontSizingEnabled();

        if (autoFontSizingEnabled.equals("true")) {
            if (width <= 827) {
                lineGap = (int) (lineGap / 1.3f);
            } else if (width > 1283) {
                lineGap = (int) (lineGap * 1.3f);
            }
        }

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

        // Настройки текста на сцене
        int vertMiddle = height / 100 * 65;
        String[] lines = {deviceName, shortDeviceName, screen, os};
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
