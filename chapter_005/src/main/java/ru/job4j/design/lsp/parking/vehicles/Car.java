package ru.job4j.design.lsp.parking.vehicles;

/**
 * Simple Car which can be parked.
 */
public class Car extends AbstractVehicle {
    /**
     * New Car object: requires 1 parking place,
     * has {@link VehicleType}.CAR type.
     *
     * @param name Name of car.
     */
    public Car(String name) {
        super(name,1, VehicleType.CAR);
    }
}
