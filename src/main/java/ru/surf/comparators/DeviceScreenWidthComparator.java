package ru.surf.comparators;

import java.util.Comparator;

import ru.surf.model.Device;

public class DeviceScreenWidthComparator implements Comparator<Device> {

    @Override
    public int compare(Device o1, Device o2) {
        return o1.getScreenWidth() - o2.getScreenWidth();
    }

}