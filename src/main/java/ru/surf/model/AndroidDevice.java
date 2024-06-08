package ru.surf.model;

import ru.surf.enums.OS;
import ru.surf.enums.Vendor;
import ru.surf.service.Loggger; 

public class AndroidDevice extends Device implements Comparable<AndroidDevice> {
    private final Loggger logger = Loggger.getInstance();

    private String ui;
    private String serialNumber;

    public AndroidDevice(
        Vendor vendor,
        String deviceName,
        String shortDeviceName,
        OS osType,
        String osVersion,
        int screenWidth,
        int screenHeight,
        double screenDiag,
        String macAddress,
        String ui,
        String serialNumber
    ){
        super(
            vendor, 
            deviceName, 
            shortDeviceName, 
            osType, 
            osVersion, 
            screenWidth, 
            screenHeight, 
            screenDiag,
            macAddress
        );

        this.ui = ui;
        this.serialNumber = serialNumber;
        logger.logInfo("Объект AndroidDevice был успешно создан");
    }

    public AndroidDevice() {
        logger.logInfo("Объект AndroidDevice был успешно создан");
    }

    public String getUi() {
        return ui;
    }

    public void setUi(String ui) {
        this.ui = ui;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((serialNumber == null) ? 0 : serialNumber.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        AndroidDevice other = (AndroidDevice) obj;
        if (serialNumber == null) {
            if (other.serialNumber != null)
                return false;
        } else if (!serialNumber.equals(other.serialNumber))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return 
        "AndroidDevice [\n" + 
        "ui=" + ui + 
        ",\nserialNumber=" + serialNumber + 
        ",\nVendor()=" + getVendor() + 
        ",\nDeviceName()=" + getDeviceName() + 
        ",\nhCode()=" + hashCode() + 
        ",\nShortDeviceName()=" + getShortDeviceName() + 
        ",\nOsType()=" + getOsType() + 
        ",\nOsVersion()=" + getOsVersion() + 
        ",\nScreenWidth()=" + getScreenWidth() + 
        ",\nScreenHeight()=" + getScreenHeight() + 
        ",\nScreenDiag()=" + getScreenDiag() + 
        ",\nMacAddress()=" + getMacAddress() + 
        "]";
    }

    @Override
    public int compareTo(AndroidDevice o) {
        return getSerialNumber().compareTo(o.getSerialNumber());
    }
}
