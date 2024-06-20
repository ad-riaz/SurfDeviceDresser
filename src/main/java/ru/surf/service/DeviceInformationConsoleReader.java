package ru.surf.service;

import java.util.Scanner;

import ru.surf.enums.AndroidVendor;
import ru.surf.enums.AppleVendor;
import ru.surf.enums.OS;
import ru.surf.enums.Vendor;
import ru.surf.model.AndroidDevice;
import ru.surf.model.AppleDevice;
import ru.surf.model.Device;

public class DeviceInformationConsoleReader {
    private static Loggger logger = Loggger.getInstance();
    private static Device device;
    private static OS os;
    private static Vendor vendor;
    private static String name;
    private static String shortName;
    private static String osVersion;
    private static int screenWidth;
    private static int screenHeight;
    private static double diagonal;

    public static Device createDevice() {
        Scanner scanner = new Scanner(System.in);

        // Считать тип ОС девайса с консоли
        while (true) {
            System.out.println("Доступные ОС: ");
            printEnumValues(OS.class);
            System.out.println("Чтобы выбрать ОС девайса, введи цифру: ");

            int osNum = 0;
            if (scanner.hasNextInt()) {
                osNum = scanner.nextInt();
                scanner.nextLine();
                if (osNum > OS.values().length || osNum < 1) {
                    System.out.println("\n\nНеверное значение!\nВведи число от 1 до " + OS.values().length + "\n");
                    continue;
                }
            } else {
                logger.logError("Ошибка ввода. Ты ввел не целое число.");
                continue;
            }

            int i = 1;
            for (OS oss : OS.values()) {
                if (osNum == i) {
                    os = oss;
                }
                i++;
            }

            break;
        }

        // Считать вендора девайса с консоли
        while (true) {
            System.out.println("Доступные вендоры устройств: ");

            if (os.equals(OS.ANDROID)) {
                printEnumValues(AndroidVendor.class);
            } else if (os.equals(OS.IOS) || os.equals(OS.IPADOS)) {
                printEnumValues(AppleVendor.class);
            }

            System.out.println("Чтобы выбрать компанию производителя девайса, введи цифру: ");
            int vendorNum = 0;
            if (scanner.hasNextInt()) {
                vendorNum = scanner.nextInt();
                scanner.nextLine();

                if (os.equals(OS.ANDROID)) {
                    if (vendorNum > AndroidVendor.values().length || vendorNum < 1) {
                        System.out.println(
                                "\n\nНеверное значение!\nВведи число от 1 до " + AndroidVendor.values().length + "\n");
                        continue;
                    }
                    vendor = AndroidVendor.values()[--vendorNum];
                } else if (os.equals(OS.IOS) || os.equals(OS.IPADOS)) {
                    if (vendorNum > AppleVendor.values().length || vendorNum < 1) {
                        System.out.println(
                                "\n\nНеверное значение!\nВведи число от 1 до " + AppleVendor.values().length + "\n");
                        continue;
                    }
                    vendor = AppleVendor.values()[--vendorNum];
                }
            } else {
                System.out.println("Ошибка ввода. Ты ввел не целое число.");
                continue;
            }
            break;
        }

        // Считать полное название девайса
        System.out.println("\n\nВведи полное название девайса (без указания компании производителя)");
        name = scanner.nextLine();

        // Считать краткое название девайса
        System.out.println("\n\nВведи краткое название девайса");
        shortName = scanner.nextLine();

        // Считать версию операционной системы
        System.out.println("\n\nВведи версию операционной системы");
        osVersion = scanner.nextLine();

        // Считать ширину экрана девайса
        while (true) {
            System.out.println("\n\nВведи ШИРИНУ экрана девайса в пикселях");
            if (scanner.hasNextInt()) {
                screenWidth = scanner.nextInt();
                scanner.nextLine();
                if(screenWidth < 1) {
                    System.out.println("\n\nОшибка ввода. Ширина экрана не может быть отрицательной или нулевой");
                    continue;
                }
            } else {
                System.out.println("\nОшибка ввода. Ты ввел не целое число.");
                continue;
            }

            break;
        }

        // Считать высоту экрана девайса
        while (true) {
            System.out.println("\n\nВведи ВЫСОТУ экрана девайса в пикселях");
            if (scanner.hasNextInt()) {
                screenHeight = scanner.nextInt();
                scanner.nextLine();
                if(screenHeight < 1) {
                    System.out.println("\n\nОшибка ввода. Высота экрана не может быть отрицательной или нулевой");
                    continue;
                }
            } else {
                System.out.println("\nОшибка ввода. Ты ввел не целое число.");
                continue;
            }

            break;
        }

        //Считать размер диагонали экрана в дюймах
        while(true) {
            System.out.println("\n\nВведи размер диагонали экрана девайса в дюймах");
            if(scanner.hasNextDouble()) {
                diagonal = scanner.nextDouble();
                scanner.nextLine();
                if (diagonal < 0.1) {
                    System.out.println("\n\nОшибка ввода. Диагональ не может быть меньше 0.1\"");
                    continue;
                }
            } else {
                System.out.println("\nОшибка ввода. Ты ввел не вещественное число.");
                continue;
            }
            break;
        }

        scanner.close();

        if (os.equals(OS.ANDROID)) {
            device = new AndroidDevice();
        } else if (os.equals(OS.IOS) || os.equals(OS.IPADOS)) {
            device = new AppleDevice();
        }

        device.setOsType(os);
        device.setOsVersion(osVersion);
        device.setVendor(vendor);
        device.setDeviceName(name);
        device.setShortDeviceName(shortName);
        device.setScreenWidth(screenWidth);
        device.setScreenHeight(screenHeight);
        device.setScreenDiag(diagonal);

        return device;
    }

    private static <E extends Enum<E>> void printEnumValues(Class<E> enumClass) {
        int i = 1;
        for (E enumValue : enumClass.getEnumConstants()) {
            System.out.println(i + ") " + enumValue);
            i++;
        }
    }
}
