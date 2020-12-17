package ru.job4j.design.lsp.foods;

import java.util.ArrayList;
import java.util.List;

/**
 * Provides different operations with
 * food in storages for redistribution
 * purposes.
 */
public class FoodHandler {
    /**
     * Collects and clears foods from
     * storages.
     *
     * @param storages List of storages.
     * @return List of all foods from all
     * storages.
     */
    public static List<Food> collectAndClear(List<Storage> storages) {
        List<Food> foods = new ArrayList<>();
        for (Storage storage : storages) {
            foods.addAll(storage.getAll());
            storage.clear();
        }
        return foods;
    }

    /**
     * Collects all food from storage and add them to the
     * given list of food. Leaves all storages empty.
     *
     * @param foodList Given food list.
     * @param storages Storages to get foods.
     */
    public static void mergeFoodListsAndClearStorages(List<Food> foodList,
                                                      List<Storage> storages) {
        foodList.addAll(collectAndClear(storages));
    }
}
