package ru.job4j.design.lsp;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract storage describes main
 * functions with food in a specific store.
 */
public abstract class AbstractStorage implements Storage {
    /**
     * Storage list.
     */
    private List<Food> foodList = new ArrayList<>();

    /**
     * Adds food to storage.
     *
     * @param food Food to add.
     */
    @Override
    public void add(Food food) {
        foodList.add(food);
    }

    /**
     * Deletes food from storage.
     *
     * @param food Food to delete.
     */
    @Override
    public void delete(Food food) {
        foodList.remove(food);
    }

    /**
     * Cleans up storage, leaves it empty.
     * Be careful with using this feature.
     */
    @Override
    public void clear() {
        foodList.clear();
    }

    /**
     * Returns all food collection
     * int the storage.
     *
     * @return List of foods
     */
    @Override
    public List<Food> getAll() {
        return foodList;
    }

    /**
     * Convert this object into String
     * format.
     *
     * @return String representation of
     * food collection in this storage.
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        foodList.forEach(el -> result.append(el).append("\n"));
        return result.toString();
    }
}
