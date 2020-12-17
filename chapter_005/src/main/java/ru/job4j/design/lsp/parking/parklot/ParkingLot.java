package ru.job4j.design.lsp.parking.parklot;

import ru.job4j.design.lsp.parking.vehicles.Vehicle;
import ru.job4j.design.lsp.parking.vehicles.VehicleType;

import java.util.Map;

/**
 * Parking lot with park/pick up
 * functions. Also available methods
 * to get state of parking.
 */
public interface ParkingLot {
    /**
     * Parks vehicle to parking lot.
     * Sets to the vehicle special
     * park number and returns it
     * as a result.
     *
     * @param vehicle Vehicle to park.
     * @return Integer park number of
     * vehicle if it was successfully
     * parked, otherwise - returns -1
     * (if there were no free places
     * in parking or vehicle was already
     * parked).
     */
    int park(Vehicle vehicle);

    /**
     * Picks vehicle up from parking lot.
     * Searches vehicle by the given
     * park number.
     *
     * @param parkNumber Vehicles park number.
     * @return Vehicle object if it was founded
     * in the parking, otherwise returns null.
     */
    Vehicle pickUpFrom(int parkNumber);

    /**
     * Picks vehicle up from parking lot.
     * Searches vehicle by the given
     * object.
     *
     * @param vehicle Vehicles to pick up.
     * @return {@code True} if vehicle was
     * founded in parking, otherwise -
     * {@code false}.
     */
    boolean pickUpFrom(Vehicle vehicle);

    /**
     * Returns total place of parking lot
     * for all types of vehicles.
     *
     * @return Number of parking places in
     * map representation.
     */
    Map<VehicleType, Integer> getTotalPlace();

    /**
     * Returns total free places of parking lot
     * for all types of vehicles
     *
     * @return Number of free parking places in
     * map representation.
     */
    Map<VehicleType, Integer> getFreePlace();
}
