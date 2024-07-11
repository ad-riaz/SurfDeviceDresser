package ru.surf;
import java.util.List;

import ru.surf.model.*;
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

        DeviceFromGoogleSheetsFactory factory = new DeviceFromGoogleSheetsFactory();
        List<Device> androidDevices = factory.createDevices("Android", null, null);
        List<Device> appleDevices = factory.createDevices("iOS", null, null);
        List<Device> ipadDevices = factory.createDevices("iPadOS", null, null);

        System.out.println(ipadDevices.size());

        for (Device device : androidDevices) {
            Scene scene = new Scene(device);
            scene.create();
            scene.exportBackground();
        }

        for (Device device : appleDevices) {
            Scene scene = new Scene(device);
            scene.create();
            scene.exportBackground();
        }

        for (Device device : ipadDevices) {
            Scene scene = new Scene(device);
            scene.create();
            scene.exportBackground();
        }
    }    
}
