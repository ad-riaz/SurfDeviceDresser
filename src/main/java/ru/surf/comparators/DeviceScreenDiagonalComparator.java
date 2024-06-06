package ru.surf.comparators;

import java.util.Comparator;

import ru.surf.model.Device;

public class DeviceScreenDiagonalComparator implements Comparator<Device> {

    @Override
    public int compare(Device o1, Device o2) {
        if(o1.getScreenDiag() < o2.getScreenDiag()) {
            return -1;
        } else if(o1.getScreenDiag() > o2.getScreenDiag()) {
            return 1;
        } else {
            return 0;
        }
    }

}
