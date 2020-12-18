package ru.job4j.design.lsp.parking.parklot;

import org.junit.Test;
import ru.job4j.design.lsp.parking.vehicles.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ParkingTest {
    Parking parking;

    Vehicle car1 = new Car("car1");
    Vehicle car2 = new Car("car2");
    Vehicle car3 = new Car("car3");
    Vehicle truck1 = new Truck("truck1", 2);
    Vehicle truck2 = new Truck("truck2", 3);
    Vehicle truck3 = new Truck("truck3", 4);

    @Test
    public void whenAddCarAndTruck() {
        parking = new SimpleParking(5, 10);
        parking.park(car1);
        parking.park(car2);
        parking.park(car3);
        parking.park(truck1);
        parking.park(truck2);
        parking.park(truck3);
        assertTrue(parking.pickUpFrom(car1));
        assertTrue(parking.pickUpFrom(car2));
        assertTrue(parking.pickUpFrom(car3));
        assertTrue(parking.pickUpFrom(truck1));
        assertTrue(parking.pickUpFrom(truck2));
        assertTrue(parking.pickUpFrom(truck3));
    }

    @Test
    public void whenAddCarAndTruckToCarParking() {
        parking = new SimpleParking(12, 0);
        parking.park(car1);
        parking.park(car2);
        parking.park(car3);
        parking.park(truck1);
        parking.park(truck2);
        parking.park(truck3);
        assertTrue(parking.pickUpFrom(car1));
        assertTrue(parking.pickUpFrom(car2));
        assertTrue(parking.pickUpFrom(car3));
        assertTrue(parking.pickUpFrom(truck1));
        assertTrue(parking.pickUpFrom(truck2));
        assertTrue(parking.pickUpFrom(truck3));
        assertThat(parking.getTotalPlace().get(ParkingType.CARS), is(0));
    }

    @Test
    public void whenNoPlaceForVehicles() {
        parking = new SimpleParking(3, 0);
        parking.park(car1);
        parking.park(truck1);
        parking.park(car2);
        parking.park(car3);
        parking.park(truck2);
        parking.park(truck3);
        assertTrue(parking.pickUpFrom(car1));
        assertTrue(parking.pickUpFrom(truck1));
        assertFalse(parking.pickUpFrom(car2));
        assertFalse(parking.pickUpFrom(car3));
        assertFalse(parking.pickUpFrom(truck2));
        assertFalse(parking.pickUpFrom(truck3));
        assertThat(parking.getTotalPlace().get(ParkingType.CARS), is(0));
    }

    @Test
    public void whenTheSameCarParksTwice() {
        parking = new SimpleParking(3, 0);
        parking.park(car1);
        assertFalse(parking.park(car1));
        assertTrue(parking.park(car2));
    }
}