package ru.job4j.design.isp;

import ru.job4j.design.isp.items.Item;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Menu with items and subitems.
 */
public class Menu {
    /**
     * Items of menu.
     */
    private final List<Item> menu;

    /**
     * Creates empty menu.
     */
    public Menu() {
        this(new ArrayList<>());
    }

    /**
     * Creates menu with the given list
     * of items.
     *
     * @param menu List of items.
     */
    public Menu(List<Item> menu) {
        this.menu = menu;
    }

    /**
     * Creates menu with the given items.
     *
     * @param items Given items.
     */
    public Menu(Item... items) {
        this();
        addItems(items);
    }

    /**
     * Adds new item to menu list.
     *
     * @param item New menu item.
     */
    public void addItem(Item item) {
        menu.add(item);
    }

    /**
     * Adds new items to menu list.
     *
     * @param items New menu items.
     */
    public void addItems(Item... items) {
        menu.addAll(Arrays.asList(items));
    }

    /**
     * Prints menu to Console.
     */
    public void print() {
        printTree("");
    }

    /**
     * Recursion function to print
     * items and their subitems to
     * the Console.
     *
     * @param pref Prefix before item's name.
     */
    private void printTree(String pref) {
        for (Item item : menu) {
            System.out.println(pref + item);
            new Menu(item.subItems()).printTree(pref + "--");
        }
    }
}
