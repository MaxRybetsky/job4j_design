package ru.job4j.design.lsp.foods;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ControlQualityTest {
    private Storage warehouse = new Warehouse();
    private Storage shop = new Shop();
    private Storage trash = new Trash();

    // Set our test control date
    private TimeCounter timeCounter
            = new TimeCounter(LocalDate.of(2020, 12, 1));

    private ControlQuality controlQuality = new ControlQuality(warehouse, shop,
            trash, timeCounter);

    List<Food> foods = new ArrayList<>();

    Food food1 = new Food("food1", LocalDate.of(2020, 11, 1),
            LocalDate.of(2022, 1, 1), 10000, 15);
    Food food2 = new Food("food2", LocalDate.of(2020, 11, 1),
            LocalDate.of(2021, 1, 1), 1000, 10);
    Food food3 = new Food("food3", LocalDate.of(2020, 11, 1),
            LocalDate.of(2020, 12, 2), 500, 20);
    Food food4 = new Food("food4", LocalDate.of(2020, 11, 1),
            LocalDate.of(2020, 11, 30), 100, 5);
    Food food5 = new Food("food5", LocalDate.of(2020, 8, 1),
            LocalDate.of(2022, 4, 14), 150, 15);
    Food food6 = new Food("food6", LocalDate.of(2020, 11, 25),
            LocalDate.of(2020, 11, 30), 10, 1);
    Food food7 = new Food("food7", LocalDate.of(2020, 6, 7),
            LocalDate.of(2020, 12, 5), 1000, 50);

    /**
     * Initializes food list.
     */
    @Before
    public void before() {
        foods.add(food1);
        foods.add(food2);
        foods.add(food3);
        foods.add(food4);
    }

    @Test
    public void whenAllAdded() {
        controlQuality.sort(foods);
        String expected = "Warehouse\n"
                + food1 + "\n";
        assertThat(warehouse.toString(), is(expected));
        expected = "Shop\n"
                + food2 + "\n"
                + food3 + "\n";
        assertThat(shop.toString(), is(expected));
        expected = "Trash\n"
                + food4 + "\n";
        assertThat(trash.toString(), is(expected));
    }

    @Test
    public void whenAddedNewAndRedistributeOld() {
        controlQuality.sort(foods);
        List<Food> newFoods = new ArrayList<>();
        newFoods.add(food5);
        newFoods.add(food6);
        newFoods.add(food7);
        FoodHandler.mergeFoodListsAndClearStorages(newFoods,
                List.of(warehouse, shop, trash));
        controlQuality.sort(newFoods);
        String expected = "Warehouse\n"
                + food5 + "\n"
                + food1 + "\n";
        assertThat(warehouse.toString(), is(expected));
        expected = "Shop\n"
                + food7 + "\n"
                + food2 + "\n"
                + food3 + "\n";
        assertThat(shop.toString(), is(expected));
        expected = "Trash\n"
                + food6 + "\n"
                + food4 + "\n";
        assertThat(trash.toString(), is(expected));
    }

    @Test
    public void whenAllRedistributed() {
        controlQuality.sort(foods);
        String expected = "Warehouse\n"
                + food1 + "\n";
        assertThat(warehouse.toString(), is(expected));
        expected = "Shop\n"
                + food2 + "\n"
                + food3 + "\n";
        assertThat(shop.toString(), is(expected));
        expected = "Trash\n"
                + food4 + "\n";
        assertThat(trash.toString(), is(expected));
        controlQuality = new ControlQuality(warehouse, shop, trash,
                new TimeCounter(LocalDate.of(2021, 1, 2)));
        foods = FoodHandler.collectAndClear(List.of(warehouse, shop, trash));
        controlQuality.sort(foods);
        expected = "Warehouse\n"
                + food1 + "\n";
        assertThat(warehouse.toString(), is(expected));
        expected = "Shop\n";
        assertThat(shop.toString(), is(expected));
        expected = "Trash\n"
                + food2 + "\n"
                + food3 + "\n"
                + food4 + "\n";
        assertThat(trash.toString(), is(expected));
    }

    @Test
    public void whenResort() {
        controlQuality.sort(foods);
        String expected = "Warehouse\n"
                + food1 + "\n";
        assertThat(warehouse.toString(), is(expected));
        expected = "Shop\n"
                + food2 + "\n"
                + food3 + "\n";
        assertThat(shop.toString(), is(expected));
        expected = "Trash\n"
                + food4 + "\n";
        assertThat(trash.toString(), is(expected));
        controlQuality = new ControlQuality(warehouse, shop, trash,
                new TimeCounter(LocalDate.of(2021, 1, 2)));
        controlQuality.resort();
        expected = "Warehouse\n"
                + food1 + "\n";
        assertThat(warehouse.toString(), is(expected));
        expected = "Shop\n";
        assertThat(shop.toString(), is(expected));
        expected = "Trash\n"
                + food2 + "\n"
                + food3 + "\n"
                + food4 + "\n";
        assertThat(trash.toString(), is(expected));
    }
}