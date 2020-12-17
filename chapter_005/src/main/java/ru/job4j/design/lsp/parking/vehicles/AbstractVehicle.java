package ru.job4j.design.lsp.parking.vehicles;

import java.util.Objects;

/**
 * Abstract vehicle which can be parked.
 */
public abstract class AbstractVehicle implements Vehicle {
    /**
     * Vehicle name.
     */
    private String vehicleName;

    /**
     * Necessary parking place.
     */
    private final int requiredParkingSpace;

    /**
     * Type of Vehicle.
     */
    private final VehicleType vehicleType;

    /**
     * Parking ID. Default number is -1
     * that means supposing that vehicle
     * isn't parked.
     */
    private int parkNumber;

    /**
     * Initializes parameters of vehicle.
     *
     * @param vehicleName          Name of vehicle.
     * @param requiredParkingSpace The given necessary
     *                             parking place.
     * @param vehicleType          Type of Vehicle.
     */
    public AbstractVehicle(String vehicleName, int requiredParkingSpace,
                           VehicleType vehicleType) {
        this.vehicleName = vehicleName;
        this.requiredParkingSpace = requiredParkingSpace;
        this.vehicleType = vehicleType;
        parkNumber = -1;
    }

    /**
     * Gets number of places required
     * for parking this vehicle.
     *
     * @return Number of places for
     * the vehicle.
     */
    @Override
    public int getRequiredParkingSpace() {
        return requiredParkingSpace;
    }

    /**
     * Returns type of vehicle as
     * element of enumeration
     * {@link VehicleType}.
     *
     * @return Type of vehicle.
     */
    @Override
    public VehicleType getType() {
        return vehicleType;
    }

    /**
     * Returns special parking id of the vehicle at
     * the parking lot. If vehicle is not at the
     * parking lot, returns -1.
     *
     * @return Parking id of vehicle or -1 if there
     * are no this vehicle at the parking lot.
     */
    @Override
    public int getParkNumber() {
        return parkNumber;
    }

    /**
     * Sets special parking id to vehicle
     * to find it at the parking lot in
     * future.
     *
     * @param parkNumber Parking id of vehicle.
     */
    @Override
    public void setParkNumber(int parkNumber) {
        this.parkNumber = parkNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractVehicle that = (AbstractVehicle) o;
        return requiredParkingSpace == that.requiredParkingSpace &&
                parkNumber == that.parkNumber &&
                Objects.equals(vehicleName, that.vehicleName) &&
                vehicleType == that.vehicleType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(vehicleName, requiredParkingSpace, vehicleType, parkNumber);
    }

    /**
     * Returns String representation
     * of vehicle.
     *
     * @return String info about the vehicle.
     */
    @Override
    public String toString() {
        return vehicleName + " "
                + vehicleType + " "
                + requiredParkingSpace + " "
                + parkNumber;
    }
}
