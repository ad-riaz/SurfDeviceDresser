package ru.surf;
import ru.surf.model.*;
import ru.surf.service.DeviceInformationConsoleReader;

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

        Device device = DeviceInformationConsoleReader.createDevice();
        Scene scene = new Scene(device);
        scene.create();
        scene.exportBackground();
    }    
}
