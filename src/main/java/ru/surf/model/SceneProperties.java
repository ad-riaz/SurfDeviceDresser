package ru.surf.model;

import ru.surf.service.AppPropertiesReader;
import ru.surf.service.FontService;
import ru.surf.service.ImageFromFileReader;

public class SceneProperties {
    private static SceneProperties instance; // Статический экземпляр синглтона
    private String backgroundPath;
    private String logoPath;
    private int logoWidthPercentage;
    private int yPosOnCanvas;
    private String fontPath;
    private String fontColor;
    private String fontSize;
    private String autoFontSizingEnabled;
    private int gapBetweenLines;
    private AppPropertiesReader propertiesReader = AppPropertiesReader.getInstance();

    private SceneProperties() {
        this.backgroundPath = propertiesReader.readProperty("backgroundPath", ImageFromFileReader.getDefaultfilePath("background.jpg"));
        this.logoPath = propertiesReader.readProperty("logoPath", ImageFromFileReader.getDefaultfilePath("logo.png"));
        this.logoWidthPercentage = propertiesReader.readIntegerValue("logoWidthPercentage", 90);
        this.yPosOnCanvas = propertiesReader.readIntegerValue("yPosOnCanvas", 100);
        this.fontPath = propertiesReader.readProperty("font", FontService.getDefaultFontFilePath("default-font.ttf"));
        this.fontColor = propertiesReader.readProperty("fontColor", "255,255,255");
        this.fontSize = propertiesReader.readProperty("fontSize", "70");
        this.autoFontSizingEnabled = propertiesReader.readProperty("autoFontSizingEnabled", "false");
        this.gapBetweenLines = propertiesReader.readIntegerValue("gapBetweenLines", 75);
    }

    // Метод для получения экземпляра синглтона
    public static SceneProperties getInstance() {
        if (instance == null) {
            synchronized (SceneProperties.class) { // Синхронизация для потокобезопасности
                if (instance == null) {
                    instance = new SceneProperties();
                }
            }
        }
        return instance;
    }

    public String getBackgroundPath() {
        return backgroundPath;
    }

    public void setBackgroundPath(String backgroundPath) {
        this.backgroundPath = backgroundPath;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }

    public int getLogoWidthPercentage() {
        return logoWidthPercentage;
    }

    public void setLogoWidthPercentage(int logoWidthPercentage) {
        this.logoWidthPercentage = logoWidthPercentage;
    }

    public int getyPosOnCanvas() {
        return yPosOnCanvas;
    }

    public void setyPosOnCanvas(int yPosOnCanvas) {
        this.yPosOnCanvas = yPosOnCanvas;
    }

    public String getFontPath() {
        return fontPath;
    }

    public void setFontPath(String fontPath) {
        this.fontPath = fontPath;
    }

    public String getFontColor() {
        return fontColor;
    }

    public void setFontColor(String fontColor) {
        this.fontColor = fontColor;
    }

    public String getFontSize() {
        return fontSize;
    }

    public void setFontSize(String fontSize) {
        this.fontSize = fontSize;
    }

    public String getAutoFontSizingEnabled() {
        return autoFontSizingEnabled;
    }

    public void setAutoFontSizingEnabled(String autoFontSizingEnabled) {
        this.autoFontSizingEnabled = autoFontSizingEnabled;
    }

    public int getGapBetweenLines() {
        return gapBetweenLines;
    }

    public void setGapBetweenLines(int gapBetweenLines) {
        this.gapBetweenLines = gapBetweenLines;
    }

    @Override
    public String toString() {
        return "SceneProperties{" +
                "backgroundPath='" + backgroundPath + '\'' +
                ", logoPath='" + logoPath + '\'' +
                ", logoWidthPercentage=" + logoWidthPercentage +
                ", yPosOnCanvas=" + yPosOnCanvas +
                ", font='" + fontPath + '\'' +
                ", fontColor='" + fontColor + '\'' +
                ", fontSize='" + fontSize + '\'' +
                ", autoFontSizingEnabled='" + autoFontSizingEnabled + '\'' +
                ", gapBetweenLines=" + gapBetweenLines +
                '}';
    }
}
