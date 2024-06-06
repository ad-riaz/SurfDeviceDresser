package ru.surf;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import ru.surf.enums.*;
import ru.surf.model.*;
import ru.surf.utils.*;

public class App {
    public static void main(String[] args) {
        Loggger logger = Loggger.getInstance();
        // Создаем устройство на основе полученных данных
        // Device device = new AppleDevice(
        //     Vendor.APPLE,
        //     "iPhone 12 mini",
        //     "IPhone12mini",
        //     OS.IOS,
        //     "14.8",
        //     1800,
        //     2340,
        //     5.42,
        //     "DC:52:85:A0:CB:43",
        //     "00008101-000D145836E0001E"
        // );

        // Device device = new AndroidDevice(
        //     Vendor.XIAOMI,
        //     "POCO X4 Pro 5G",
        //     "PocoX4pro",
        //     OS.ANDROID,
        //     "11",
        //     1800,
        //     2400,
        //     6.67,
        //     "fc:02:96:1e:34:9b",
        //     "MIUI 13.0.1",
        //     "38446/K2Q900384"
        // );

        Device device = new AndroidDevice(
            Vendor.XIAOMI,
            "Redmi Note 8 Pro",
            "Note8Pro",
            OS.ANDROID,
            "11",
            1080,
            2340,
            6.53,
            "fc:02:96:1e:34:9b",
            "MIUI 13.0.1",
            "38446/K2Q900384"
        );

        Scene scene = new Scene(device);
        scene.create();
        scene.exportBackground();
    }

    
}
