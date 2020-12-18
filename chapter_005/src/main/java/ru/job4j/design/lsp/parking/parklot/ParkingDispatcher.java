package ru.job4j.design.lsp.parking.parklot;

import ru.job4j.design.lsp.parking.vehicles.Vehicle;

import java.util.Map;
import java.util.function.Predicate;

/**
 * Simple dispatcher to choosing
 * right place for vehicle parking.
 */
public class ParkingDispatcher {
    Map<Predicate<Vehicle>, ParkingType> dispatch;

    public ParkingDispatcher() {
        init();
    }

    private void init() {
        dispatch.put(vehicle -> vehicle.size() == 1, ParkingType.CARS);
        dispatch.put(vehicle -> vehicle.size() > 1, ParkingType.TRUCKS);
    }

    public ParkingType chooseParking(Vehicle vehicle) {
        for(Predicate<Vehicle> predicate: dispatch.keySet()) {
            if(predicate.test(vehicle)) {
                return dispatch.get(predicate);
            }
        }
        return null;
    }
}
