package ru.surf.enums;

public enum AndroidVendor implements Vendor {
    ASCOM("Ascom"),
    BLACKBERRY("BlackBerry"),
    BLU("BLU"),
    BLUEBIRD("Bluebird"),
    BQ("BQ"),
    BULLITT_GROUP_LIMITED("Bullitt Group Limited"),
    CAT("Cat"),
    CHAINWAY("Chainway"),
    CIPHERLAB_CIPHERLAB("CipherLab CipherLab"),
    CROSSCALL("CROSSCALL"),
    CYRUS("Cyrus"),
    DATALOGIC("Datalogic"),
    PEPPERL_FUCHS_GMBH("Pepperl+Fuchs GmbH"),
    ELO_TOUCH_SOLUTIONS("Elo Touch Solutions"),
    FUJITSU("Fujitsu"),
    GETAC("Getac"),
    GIGASET("Gigaset"),
    GOOGLE("Google"),
    HONEYWELL("Honeywell"),
    HONOR("Honor"),
    HTC("HTC"),
    HUAWEI("Huawei"),
    IWAYLINK("iWaylink"),
    JANAM_TECHNOLOGIES("Janam Technologies"),
    KYOCERA("KYOCERA"),
    LENOVO("LENOVO"),
    LG("LG"),
    M3_MOBILE("M3 Mobile"),
    MOBILEDEMAND("MobileDemand"),
    MOBIWIRE("MobiWire"),
    MOTOROLA("Motorola"),
    MOTOROLA_SOLUTIONS("Motorola Solutions"),
    MYPHONE("myPhone"),
    MULTILASER("Multilaser"),
    NOKIA("Nokia"),
    NOTHING_TECH("Nothing Technology Ltd"),
    HMD_GLOBAL("HMD Global"),
    ONEPLUS("OnePlus"),
    OPPO("OPPO"),
    OPTICON("Opticon"),
    PANASONIC("PANASONIC"),
    POCO("POCO"),
    POINTMOBILE("PointMobile"),
    POSITIVO("Positivo"),
    RHINO("RHINO"),
    SAMSUNG("Samsung"),
    SHARP("SHARP"),
    SONIMTECH("Sonimtech"),
    SONY("Sony"),
    SPECTRALINK("Spectralink"),
    TCL("TCL"),
    UMIDGI("UMIDGI"),
    UNITECH_ELECTRONICS("Unitech Electronics"),
    UROVO("Urovo"),
    VIVO("Vivo"),
    VSMART("Vsmart"),
    XIAOMI("Xiaomi"),
    WIKO("WIKO"),
    WISHTEL("Wishtel"),
    ZEBRA_TECHNOLOGIES("Zebra Technologies"),
    ZTE("ZTE");

    private String vendorName;

    AndroidVendor(String vendorName) {
        this.vendorName = vendorName;
    }

    public String toString() {
        return vendorName;
    }
}
