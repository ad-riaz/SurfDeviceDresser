package ru.surf.enums;

public enum OS {
    IOS("iOS"),
    IPADOS("iPadOS"),
    ANDROID("Android");

    private String os;

    OS(String os) {
        this.os = os;
    }

    public String toString() {
        return os;
    }
}
