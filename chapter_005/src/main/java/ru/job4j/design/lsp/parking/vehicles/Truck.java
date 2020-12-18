package ru.job4j.design.lsp.parking.vehicles;

/**
 * Simple Truck.
 */
public class Truck extends AbstractVehicle {
    /**
     * New Truck object: requires {@code size}
     * parking place.
     *
     * @param name Name of truck.
     * @param size Necessary parking place.
     */
    public Truck(String name, int size) {
        super(name, size);
    }
}
