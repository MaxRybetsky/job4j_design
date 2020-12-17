package ru.job4j.design.lsp.foods;

/**
 * Keeps food list in the shop.
 */
public class Shop extends AbstractStorage {
    /**
     * Converts info about products in
     * the shop to string format.
     *
     * @return String info about food
     * in the shop.
     */
    @Override
    public String toString() {
        return "Shop\n" + super.toString();
    }
}
