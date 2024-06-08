package ru.surf.model;

import ru.surf.enums.OS;
import ru.surf.enums.Vendor;
import ru.surf.service.Loggger; 

public class AppleDevice extends Device implements Comparable<AppleDevice> {
    private final Loggger logger = Loggger.getInstance();
    private String udid;
    
    public AppleDevice(
        Vendor vendor, 
        String deviceName, 
        String shortDeviceName, 
        OS osType, 
        String osVersion,
        int screenWidth, 
        int screenHeight, 
        double screenDiag, 
        String macAddress, 
        String udid
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
        this.udid = udid;
        logger.logInfo("Объект AppleDevice был успешно создан");
    }    

    public AppleDevice() {
        logger.logInfo("Объект AppleDevice был успешно создан");
    }

    public String getUdid() {
        return udid;
    }

    public void setUdid(String udid) {
        this.udid = udid;
    }

    @Override
    public String toString() {
        return 
        "AppleDevice [\n"+ 
        "UDID()=" + getUdid() + 
        ", \nVendor()=" + getVendor() + 
        ", \nDeviceName()=" + getDeviceName() + 
        ", \nShortDeviceName()=" + getShortDeviceName() + 
        ", \nOsType()=" + getOsType() + 
        ", \nOsVersion()=" + getOsVersion() + 
        ", \nScreenWidth()=" + getScreenWidth() + 
        ", \nScreenHeight()=" + getScreenHeight() + 
        ", \nScreenDiag()=" + getScreenDiag() + 
        ", \nMacAddress()=" + getMacAddress() + 
        "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((udid == null) ? 0 : udid.hashCode());
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
        AppleDevice other = (AppleDevice) obj;
        if (udid == null) {
            if (other.udid != null)
                return false;
        } else if (!udid.equals(other.udid))
            return false;
        return true;
    }

    @Override
    public int compareTo(AppleDevice ed) {
        return getUdid().compareTo(ed.getUdid());
    }
}
