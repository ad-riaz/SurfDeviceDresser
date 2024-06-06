package ru.surf.comparators;

import java.util.Comparator;

import ru.surf.model.Device;

public class DeviceOsTypeComparator implements Comparator<Device> {

    @Override
    public int compare(Device o1, Device o2) {
        return o1.getOsType().toString().compareTo(o2.getOsType().toString());
    }
}
