package ru.surf.service;

import java.awt.Color;

public class ColorCreator {
    private static Loggger logger = Loggger.getInstance();

    public static Color create(String colorFromProperties) {
        Color defaultColor = Color.white;
        String[] colorStrings = colorFromProperties.split(",");
        int red = 0;
        int green = 0;
        int blue = 0;

        for (int i = 0; i < colorStrings.length; i++ ) {
            try {
                int color = Integer.valueOf(colorStrings[i]);
                if (color >= 0 && color < 256) {
                    if (i == 0) {
                        red = color;
                    } else if (i == 1) {
                        green = color;
                    } else if (i == 2) {
                        blue = color;
                    }
                } else {
                    throw new NumberFormatException();
                }                
            } catch (NumberFormatException e) {
                logger.logError("Не получилось создать цвет из строки \"" + 
                colorFromProperties + "\". Вместо него был использован белый");
                return defaultColor;
            }
        }

        return new Color(red, green, blue);
    }
}
