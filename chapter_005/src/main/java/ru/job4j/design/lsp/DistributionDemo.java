package ru.job4j.design.lsp;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DistributionDemo {
    public static void main(String[] args) {
        List<Food> foodList = new ArrayList<>();
        foodList.add(
                new Food("Apple", LocalDate.of(2020,12,31),
                        LocalDate.of(2020,11,11), 100, 10)
        );
        foodList.add(
                new Food("Cheese", LocalDate.of(2020,12,1),
                        LocalDate.of(2020,11,11), 1100, 20)
        );
        foodList.add(
                new Food("Vine", LocalDate.of(2022,12,31),
                        LocalDate.of(2020,11,11), 10000, 10)
        );
        foodList.add(
                new Food("Potato", LocalDate.of(2020,12,17),
                        LocalDate.of(2020,11,11), 100, 10)
        );
        Storage warehouse = new Warehouse();
        Storage shop = new Shop();
        Storage trash = new Trash();
        ControlQuality q = new ControlQuality(warehouse, shop, trash,
                new TimeCounter(LocalDate.of(2020,11,12)));
        q.sort(foodList);
        System.out.println(warehouse);
        System.out.println(shop);
        System.out.println(trash);
        System.out.println("======================");
        foodList = new ArrayList<>();
        foodList.add(
                new Food("Apple1", LocalDate.of(2020,12,31),
                        LocalDate.of(2020,11,11), 100, 10)
        );
        foodList.add(
                new Food("Cheese1", LocalDate.of(2020,12,1),
                        LocalDate.of(2020,11,11), 1100, 20)
        );
        foodList.add(
                new Food("Vine1", LocalDate.of(2022,12,31),
                        LocalDate.of(2020,11,11), 10000, 10)
        );
        foodList.add(
                new Food("Potato1", LocalDate.of(2020,12,17),
                        LocalDate.of(2020,11,11), 100, 10)
        );
        FoodHandler.mergeFoodListsAndClearStorages(foodList, List.of(warehouse, shop, trash));
        q.sort(foodList);
        System.out.println(warehouse);
        System.out.println(shop);
        System.out.println(trash);
    }
}
