package ru.surf;

import ru.surf.enums.AndroidVendor;
import ru.surf.enums.OS;
import ru.surf.model.*;
import ru.surf.service.DeviceInformationConsoleReader;

public class App {
    public static void main(String[] args) {
        Device device = DeviceInformationConsoleReader.createDevice();
        
        // Device device = new AndroidDevice(
        //     AndroidVendor.XIAOMI,
        //     "Redmi Note 8 Pro",
        //     "Note8Pro",
        //     OS.ANDROID,
        //     "11",
        //     1080,
        //     2340,
        //     6.53,
        //     "fc:02:96:1e:34:9b",
        //     "MIUI 13.0.1",
        //     "38446/K2Q900384"
        // );


        Scene scene = new Scene(device);
        scene.create();
        scene.exportBackground();
    }    
}
