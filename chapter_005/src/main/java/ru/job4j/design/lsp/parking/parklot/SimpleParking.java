package ru.job4j.design.lsp.parking.parklot;

import ru.job4j.design.lsp.parking.vehicles.Vehicle;

import java.util.Map;

/**
 * Simple parking.
 */
public class SimpleParking implements Parking {
    public SimpleParking(int cars, int trucks) {

    }

    @Override
    public boolean park(Vehicle vehicle) {
        return false;
    }

    @Override
    public boolean pickUpFrom(Vehicle vehicle) {
        return false;
    }

    @Override
    public Map<ParkingType, Integer> getTotalPlace() {
        return null;
    }

    @Override
    public Map<ParkingType, Integer> getFreePlace() {
        return null;
    }
}
