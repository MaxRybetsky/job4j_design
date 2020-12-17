package ru.job4j.design.lsp.parking.parklot;

import ru.job4j.design.lsp.parking.vehicles.Vehicle;
import ru.job4j.design.lsp.parking.vehicles.VehicleType;

import java.util.Map;

/**
 * Simple parking.
 */
public class SimpleParking implements ParkingLot {
    public SimpleParking(int cars, int trucks) {

    }

    @Override
    public int park(Vehicle vehicle) {
        return -1;
    }

    @Override
    public Vehicle pickUpFrom(int parkNumber) {
        return null;
    }

    @Override
    public boolean pickUpFrom(Vehicle vehicle) {
        return false;
    }

    @Override
    public Map<VehicleType, Integer> getTotalPlace() {
        return null;
    }

    @Override
    public Map<VehicleType, Integer> getFreePlace() {
        return null;
    }
}
