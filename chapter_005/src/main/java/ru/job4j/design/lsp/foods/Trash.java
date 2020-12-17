package ru.job4j.design.lsp.foods;

/**
 * Keeps food list in the trash.
 */
public class Trash extends AbstractStorage {
    /**
     * Converts info about products in
     * the trash to string format.
     *
     * @return String info about food
     * in the trash.
     */
    @Override
    public String toString() {
        return "Trash\n" + super.toString();
    }
}
