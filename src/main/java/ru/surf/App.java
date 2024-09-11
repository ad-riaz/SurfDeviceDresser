package ru.surf;
import java.util.List;

import ru.surf.model.*;
import ru.surf.service.AppPropertiesReader;
import ru.surf.service.DeviceFromGoogleSheetsFactory;


public class App {
    public static void main(String[] args) {
        System.out.println(
            "\n" + //
            "\n" + //
            "                      ++++++++++++                               \n" + //
            "                    +++++++++   ++++                             \n" + //
            "               ++++++++++         + +                            \n" + //
            "            +++++++ +               +                            \n" + //
            "         ++++++ +                   ++                           \n" + //
            "       ++++ +                     ++                             \n" + //
            "     +++ +                       +++                             \n" + //
            "   +++ +                      +++++                        ++    \n" + //
            "  ++ ++                    +++++++           ++          ++++    \n" + //
            " ++ ++                 ++ ++++++  +++    ++++++         +++      \n" + //
            "+++ +                ++++++++    ++++   +++++++++      +++       \n" + //
            "+++                +++++++     ++++  ++    ++ ++++   +++         \n" + //
            "+++++                        ++++   +++   ++   ++ + +++          \n" + //
            " +++++++++                 +++++  ++++   +   ++    +++  +++++++  \n" + //
            "  ++++++++++++++        +++ ++   ++++      +++     ++++++++++ + +\n" + //
            "      +++++++++++++++      +++++ +++     +++    +++++++          \n" + //
            "               +++++++++   ++++  ++    +++++ +++  ++ ++          \n" + //
            "                      +++  +++  ++++   ++++++    +++ ++          \n" + //
            "                        ++  +    +++    ++++     +++  +          \n" + //
            "                ++      ++       +++++   +       +++ +           \n" + //
            "              + +     ++++   ++++ ++             ++++            \n" + //
            "            + ++++++++++    +++++                 ++++           \n" + //
            "            +   ++++++     +++                                   \n" + //
            "                           +                                     \n" + //
            "\n" + //
            "");

        AppPropertiesReader propertiesReader = AppPropertiesReader.getInstance();

        DeviceFromGoogleSheetsFactory factory = new DeviceFromGoogleSheetsFactory();
        List<Device> androidDevices = factory.createDevices("Android", null, null);
        List<Device> appleDevices = factory.createDevices("iOS", null, null);
        List<Device> ipadDevices = factory.createDevices("iPadOS", null, null);

        Scene scene = new Scene();
        for (Device device : androidDevices) {
            scene.init(device);
            scene.exportBackground();
        }

        for (Device device : appleDevices) {
            scene.init(device);
            scene.exportBackground();
        }

        for (Device device : ipadDevices) {
            scene.init(device);
            scene.exportBackground();
        }



    }    
}
