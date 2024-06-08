package ru.surf.enums;

public enum AppleVendor implements Vendor {
    APPLE("Apple");

    private String vendorName;

    AppleVendor(String vendorName) {
        this.vendorName = vendorName;
    }

    public String toString() {
        return vendorName;
    }
}
