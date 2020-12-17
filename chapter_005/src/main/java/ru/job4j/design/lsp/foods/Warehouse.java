package ru.job4j.design.lsp.foods;

/**
 * Keeps food list in the warehouse.
 */
public class Warehouse extends AbstractStorage {
    /**
     * Converts info about products in
     * the warehouse to string format.
     *
     * @return String info about food
     * in the warehouse.
     */
    @Override
    public String toString() {
        return "Warehouse\n" + super.toString();
    }
}
