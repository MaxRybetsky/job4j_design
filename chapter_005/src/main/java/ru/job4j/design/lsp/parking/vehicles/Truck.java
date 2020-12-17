package ru.job4j.design.lsp.parking.vehicles;

/**
 * Simple Truck which can be parked.
 */
public class Truck extends AbstractVehicle {
    /**
     * New Truck object: requires {@code requiredParkingSpace}
     * parking place, has {@link VehicleType}.TRUCK type.
     *
     * @param name                 Name of truck.
     * @param requiredParkingSpace Necessary parking place.
     */
    public Truck(String name, int requiredParkingSpace) {
        super(name, requiredParkingSpace, VehicleType.TRUCK);
    }
}
