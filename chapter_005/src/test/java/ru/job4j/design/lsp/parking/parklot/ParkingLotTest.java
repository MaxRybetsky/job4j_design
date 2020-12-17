package ru.job4j.design.lsp.parking.parklot;

import org.junit.Test;
import ru.job4j.design.lsp.parking.vehicles.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ParkingLotTest {
    ParkingLot parking;

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
        assertThat(parking.pickUpFrom(car1.getParkNumber()), is(car1));
        assertThat(parking.pickUpFrom(car2.getParkNumber()), is(car2));
        assertThat(parking.pickUpFrom(car3.getParkNumber()), is(car3));
        assertThat(parking.pickUpFrom(truck1.getParkNumber()), is(truck1));
        assertThat(parking.pickUpFrom(truck2.getParkNumber()), is(truck2));
        assertThat(parking.pickUpFrom(truck3.getParkNumber()), is(truck3));
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
        assertThat(parking.pickUpFrom(car1.getParkNumber()), is(car1));
        assertThat(parking.pickUpFrom(car2.getParkNumber()), is(car2));
        assertThat(parking.pickUpFrom(car3.getParkNumber()), is(car3));
        assertThat(parking.pickUpFrom(truck1.getParkNumber()), is(truck1));
        assertThat(parking.pickUpFrom(truck2.getParkNumber()), is(truck2));
        assertThat(parking.pickUpFrom(truck3.getParkNumber()), is(truck3));
        assertThat(parking.getTotalPlace().get(VehicleType.CAR), is(0));
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
        assertThat(parking.pickUpFrom(car1.getParkNumber()), is(car1));
        assertThat(parking.pickUpFrom(truck1.getParkNumber()), is(truck1));
        assertNull(parking.pickUpFrom(car2.getParkNumber()));
        assertThat(parking.pickUpFrom(car3), is(false));
        assertNull(parking.pickUpFrom(truck2.getParkNumber()));
        assertNull(parking.pickUpFrom(truck3.getParkNumber()));
        assertThat(parking.getTotalPlace().get(VehicleType.CAR), is(0));
    }
}