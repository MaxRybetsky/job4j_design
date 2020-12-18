package ru.job4j.design.lsp.parking.vehicles;

import java.util.Objects;

/**
 * Abstract vehicle.
 */
public abstract class AbstractVehicle implements Vehicle {
    /**
     * Vehicle name.
     */
    private final String vehicleName;

    /**
     * Necessary parking place.
     */
    private final int size;

    /**
     * Initializes parameters of vehicle.
     *
     * @param vehicleName Name of vehicle.
     * @param size        The given necessary
     *                    parking place.
     */
    public AbstractVehicle(String vehicleName, int size) {
        this.vehicleName = vehicleName;
        this.size = size;
    }

    /**
     * Gets number of places required
     * for parking this vehicle.
     *
     * @return Number of places for
     * the vehicle.
     */
    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AbstractVehicle that = (AbstractVehicle) o;
        return size == that.size
                && Objects.equals(vehicleName, that.vehicleName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vehicleName, size);
    }

    /**
     * Returns String representation
     * of vehicle.
     *
     * @return String info about the vehicle.
     */
    @Override
    public String toString() {
        return vehicleName + " " + size;
    }
}
