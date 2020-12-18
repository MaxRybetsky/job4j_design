package ru.job4j.design.lsp.parking.vehicles;

/**
 * Vehicle which can be placed
 * into parking.
 */
public interface Vehicle {
    /**
     * Gets number of places required
     * for parking this vehicle.
     *
     * @return Number of places for
     * the vehicle.
     */
    int size();
}
