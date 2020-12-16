package ru.job4j.design.lsp;

import java.util.List;

/**
 * Describes main operations with
 * food in the storage.
 */
public interface Storage {
    /**
     * Adds food to storage.
     *
     * @param food Food to add.
     */
    void add(Food food);

    /**
     * Returns all food collection
     * int the storage.
     *
     * @return List of foods
     */
    List<Food> getAll();

    /**
     * Deletes food from storage.
     *
     * @param food Food to delete.
     */
    void delete(Food food);

    /**
     * Cleans up storage, leaves it empty.
     * Be careful with using this feature.
     */
    void clear();
}
