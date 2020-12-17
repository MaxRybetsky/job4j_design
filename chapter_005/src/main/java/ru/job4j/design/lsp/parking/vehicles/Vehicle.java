package ru.job4j.design.lsp.parking.vehicles;

/**
 * Vehicle which can be placed
 * into parking. Contains methods
 * describe this process.
 */
public interface Vehicle {
    /**
     * Gets number of places required
     * for parking this vehicle.
     *
     * @return Number of places for
     * the vehicle.
     */
    int getRequiredParkingSpace();

    /**
     * Returns type of vehicle as
     * element of enumeration
     * {@link VehicleType}.
     *
     * @return Type of vehicle.
     */
    VehicleType getType();

    /**
     * Sets special parking id to vehicle
     * to find it at the parking lot in
     * future.
     *
     * @param parkNumber Parking id of vehicle.
     */
    void setParkNumber(int parkNumber);

    /**
     * Returns special parking id of the vehicle at
     * the parking lot. If vehicle is not at the
     * parking lot, returns -1.
     *
     * @return Parking id of vehicle or -1 if there
     * are no this vehicle at the parking lot.
     */
    int getParkNumber();
}
