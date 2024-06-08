package ru.surf.model;

import ru.surf.enums.OS;
import ru.surf.enums.Vendor;
import ru.surf.service.Loggger; 

public abstract class Device {
    protected Vendor vendor;
    protected String deviceName;
    protected String shortDeviceName;
    protected OS osType;
    protected String osVersion;
    protected int screenWidth;
    protected int screenHeight;
    protected double screenDiag;
    protected String macAddress;
    
    public Device(
        Vendor vendor,
        String deviceName,
        String shortDeviceName,
        OS osType,
        String osVersion,
        int screenWidth,
        int screenHeight,
        double screenDiag,
        String macAddress
    ){
        this.vendor = vendor;
        this.deviceName = deviceName;
        this.shortDeviceName = shortDeviceName;
        this.osType = osType;
        this.osVersion = osVersion;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.screenDiag = screenDiag;
        this.macAddress = macAddress;
    }

    public Device() {
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getShortDeviceName() {
        return shortDeviceName;
    }

    public void setShortDeviceName(String shortDeviceName) {
        this.shortDeviceName = shortDeviceName;
    }

    public OS getOsType() {
        return osType;
    }

    public void setOsType(OS osType) {
        this.osType = osType;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public void setScreenWidth(int screenWidth) {
        this.screenWidth = screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public void setScreenHeight(int screenHeight) {
        this.screenHeight = screenHeight;
    }

    public double getScreenDiag() {
        return screenDiag;
    }

    public String getDiagonal() {
        return screenDiag + "\"";
    }

    public void setScreenDiag(double screenDiag) {
        this.screenDiag = screenDiag;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }
}