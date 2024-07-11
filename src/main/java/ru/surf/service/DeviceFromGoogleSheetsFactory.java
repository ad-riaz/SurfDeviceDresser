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

            if (values == null || values.isEmpty()) {
                logger.logWarning("No data found from Google Sheets.");
                return devices;
            } else {
                for (int j = 1; j < values.size(); j++) {
                    Device device;
                    OS os;

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
                        switch (i) {
                            case 0:
                                device.setVendor(values.get(j).get(i).toString());
                                break;
                            case 1:
                                device.setDeviceName(values.get(j).get(i).toString());
                                break;
                            case 2:
                                device.setOsVersion(values.get(j).get(i).toString());
                                break;
                            case 3:
                                device.setShortDeviceName(values.get(j).get(i).toString());
                                break;
                            case 4:
                                device.setScreenWidth(Integer.parseInt(values.get(j).get(i).toString()));
                                break;
                            case 5:
                                device.setScreenHeight(Integer.parseInt(values.get(j).get(i).toString()));
                                break;
                            case 6:
                                device.setScreenDiag(Double.parseDouble(values.get(j).get(i).toString()));
                                break;
                        }
                    }
                    devices.add(device);
                }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (GeneralSecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            return devices;
        }
    }
}
