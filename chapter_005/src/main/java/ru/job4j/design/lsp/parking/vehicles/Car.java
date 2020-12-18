package ru.job4j.design.lsp.parking.vehicles;

/**
 * Simple Car.
 */
public class Car extends AbstractVehicle {
    /**
     * New Car object: requires 1 parking place.
     *
     * @param name Name of car.
     */
    public Car(String name) {
        super(name, 1);
    }
}
