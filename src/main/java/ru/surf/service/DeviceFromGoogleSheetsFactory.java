package ru.surf.service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

import com.google.api.services.sheets.v4.model.ValueRange;

import ru.surf.enums.OS;
import ru.surf.model.AndroidDevice;
import ru.surf.model.AppleDevice;
import ru.surf.model.Device;

public class DeviceFromGoogleSheetsFactory {
    Loggger logger = Loggger.getInstance();

    public List<Device> createDevices(String OSType, String rangeStart, String rangeEnd) {
        ValueRange response = null;
        List<Device> devices = new ArrayList<>();
        List<List<Object>> values = new ArrayList<>();

        try {
            response = GoogleSheetsReader.getValues(OSType, rangeStart, rangeEnd);
            values = response.getValues();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return devices;
        } catch (GeneralSecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return devices;
        }

        if (values == null || values.isEmpty()) {
            logger.logWarning("No data found from Google Sheets.");
            return devices;
        } else {
            for (int j = 1; j < values.size(); j++) {
                Device device;
                OS os;
                boolean areDeviceFieldsCorrect = true;

                if (OSType.toLowerCase().equals("ios")) {
                    device = new AppleDevice();
                    os = OS.IOS;
                } else if (OSType.toLowerCase().equals("ipados")) {
                    device = new AppleDevice();
                    os = OS.IPADOS;
                } else {
                    device = new AndroidDevice();
                    os = OS.ANDROID;
                }

                device.setOsType(os);

                for (int i = 0; i < values.get(j).size(); i++) {
                    String value = values.get(j).get(i).toString();

                    if (i <= 6) {
                        if (value.isEmpty() || value == null) {
                            areDeviceFieldsCorrect = false;
                            int gSheetsLine = j + 1;
                            logger.logError("Некоторые данные об устройстве в строке " + gSheetsLine + " в таблице '" + OSType + "' пусты! Изображение для него не было создано.\nЗаполни отсутствующую информацию и повтори процесс генерации изображений.");
                            break;
                        }
                    }

                    switch (i) {
                        case 0:
                            device.setVendor(value);
                            break;
                        case 1:
                            device.setDeviceName(value);
                            break;
                        case 2:
                            device.setOsVersion(value);
                            break;
                        case 3:
                            device.setShortDeviceName(value);
                            break;
                        case 4:
                            try {
                                Integer.parseInt(value);
                            } catch(NumberFormatException e) {
                                logger.logError("Не удалось создать изображение для устройства, так как поле 'Ширина экрана' в таблице имеет не корректный формат!\n Проверь данные в исходной таблице со списком устройств в ячейке в строке " + i+1 + " и столбце " + j+1 + " - значение должно быть типа integer.");
                                continue;
                            }
                            device.setScreenWidth(Integer.parseInt(value));
                        case 5:
                            try {
                                Integer.parseInt(value);
                            } catch(NumberFormatException e) {
                                logger.logError("Не удалось создать изображение для устройства, так как поле 'Высота экрана' в таблице имеет не корректный формат!\n Проверь данные в исходной таблице со списком устройств в ячейке в строке " + i+1 + " и столбце " + j+1 + " - значение должно быть типа integer.");
                                continue;
                            }
                            device.setScreenHeight(Integer.parseInt(value));
                        case 6:
                            try {
                                Double.parseDouble(value);
                            } catch (NumberFormatException e){
                                logger.logError("Не удалось создать изображение для устройства, так как поле 'Диагональ экрана' в таблице имеет не корректный формат!\n Проверь данные в исходной таблице со списком устройств в ячейке в строке " + i+1 + " и столбце " + j+1 + " - значение должно быть типа double.");
                                continue;
                            }
                            device.setScreenDiag(Double.parseDouble(value));
                    }
                }
                if (areDeviceFieldsCorrect) {
                    devices.add(device);
                }
            }
        }

        return devices;
    }
}
