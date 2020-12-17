package ru.job4j.design.lsp.foods;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Main distributor of foods by
 * expired date value.
 */
public class StorageDispatcher {
    /**
     * The warehouse storage.
     */
    private final Storage warehouse;

    /**
     * The shop storage.
     */
    private final Storage shop;

    /**
     * The trash storage.
     */
    private final Storage trash;

    /**
     * Time counter to sort foods.
     */
    private final TimeCounter timeCounter;

    /**
     * Dispatcher for food
     * distribution.
     */
    private final Map<Predicate<Food>, Consumer<Food>> dispatch = new HashMap<>();

    /**
     * Initializes values of storages and set default value
     * to time counter.
     *
     * @param warehouse The warehouse storage.
     * @param shop      The shop storage.
     * @param trash     The trash storage.
     */
    public StorageDispatcher(Storage warehouse, Storage shop, Storage trash) {
        this(warehouse, shop, trash, new TimeCounter());
    }

    /**
     * Initializes values of storages, set custom value
     * to time counter and start process of initializing
     * dispatcher by control values.
     *
     * @param warehouse   The warehouse storage.
     * @param shop        The shop storage.
     * @param trash       The trash storage.
     * @param timeCounter Custom time counter.
     */
    public StorageDispatcher(Storage warehouse, Storage shop,
                             Storage trash, TimeCounter timeCounter) {
        this.warehouse = warehouse;
        this.shop = shop;
        this.trash = trash;
        this.timeCounter = timeCounter == null ? new TimeCounter() : timeCounter;
        init();
    }

    /**
     * Takes {@link Food}'s object and checks its
     * expired date via dispatcher. Then adds the
     * food to the suitable storage.
     *
     * @param food Food to distribute.
     */
    public void distribution(Food food) {
        for (Predicate<Food> predicate : dispatch.keySet()) {
            if (predicate.test(food)) {
                this.dispatch.get(predicate).accept(food);
            }
        }
    }

    /**
     * Initializes dispatcher by control values
     * of expiration dates.
     */
    private void init() {
        dispatch.put(
                food -> timeCounter.getExpiredPercentage(food) < 25,
                warehouse::add
        );
        dispatch.put(
                food -> timeCounter.getExpiredPercentage(food) >= 25
                        && timeCounter.getExpiredPercentage(food) < 75,
                shop::add
        );
        dispatch.put(
                food -> timeCounter.getExpiredPercentage(food) >= 75
                        && timeCounter.getExpiredPercentage(food) < 100,
                food -> {
                    food.setActivateDiscount(true);
                    shop.add(food);
                }
        );
        dispatch.put(
                food -> timeCounter.getExpiredPercentage(food) >= 100,
                trash::add
        );
    }
}
