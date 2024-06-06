package ru.surf.utils;

public class Utilities {

    public static String splitLongString(String longString) {
        int length = longString.length();
        int middle = length / 2;

        while (longString.charAt(middle) != ' ' && middle < length - 1) {
            middle++;
        }

        String firstHalf = longString.substring(0, middle);
        String secondHalf = longString.substring(middle).trim();

        return firstHalf + "\n" + secondHalf;
    }
}
