package ru.job4j.design.lsp.foods;

import java.util.List;

/**
 * Processes control of
 * food's quality.
 */
public class ControlQuality {
    /**
     * Dispatcher to sort products
     * by expired date.
     */
    private final StorageDispatcher dispatcher;

    /**
     * List of storages.
     */
    private List<Storage> storages;

    /**
     * Simple constructor to initialize dispatcher
     * with default time counter value.
     *
     * @param warehouse The warehouse storage.
     * @param shop      The shop storage.
     * @param trash     The trash storage.
     */
    public ControlQuality(Storage warehouse, Storage shop, Storage trash) {
        this(warehouse, shop, trash, null);
    }

    /**
     * Simple constructor to initialize dispatcher.
     * Adds storages to list.
     *
     * @param warehouse   The warehouse storage.
     * @param shop        The shop storage.
     * @param trash       The trash storage.
     * @param timeCounter The custom time counter.
     */
    public ControlQuality(Storage warehouse, Storage shop,
                          Storage trash, TimeCounter timeCounter) {
        this.dispatcher = new StorageDispatcher(warehouse, shop, trash, timeCounter);
        storages = List.of(warehouse, shop, trash);
    }

    /**
     * Sorts products by their expired date
     * via dispatcher.
     *
     * @param foodList List of foods.
     */
    public void sort(List<Food> foodList) {
        for (Food food : foodList) {
            dispatcher.distribution(food);
        }
    }

    /**
     * Resorts food in food storages.
     */
    public void resort() {
        List<Food> foodList = FoodHandler.collectAndClear(storages);
        sort(foodList);
    }
}
