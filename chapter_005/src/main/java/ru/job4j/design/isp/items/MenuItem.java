package ru.job4j.design.isp.items;

import ru.job4j.design.isp.actions.Action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Menu item.
 */
public class MenuItem implements Item {
    /**
     * Item's name.
     */
    private final String itemName;

    /**
     * Item's action.
     */
    private final Action action;

    /**
     * List of item's children.
     */
    private final List<Item> children = new ArrayList<>();

    /**
     * Constructor of simple item without children.
     *
     * @param itemName Given item's name.
     * @param action   Given item's action.
     */
    public MenuItem(String itemName, Action action) {
        this(itemName, action, new Item[]{});
    }

    /**
     * Constructor of simple item with children.
     *
     * @param itemName Given item's name.
     * @param action   Given item's action.
     * @param items    Children of the item.
     */
    public MenuItem(String itemName, Action action, Item... items) {
        this.itemName = itemName;
        this.action = action;
        add(items);
    }

    /**
     * Adds given items to children list.
     *
     * @param items New children of item.
     */
    private void add(Item... items) {
        children.addAll(Arrays.asList(items));
    }

    /**
     * Name of item.
     *
     * @return Item's string name.
     */
    @Override
    public String name() {
        return itemName;
    }

    /**
     * Item's action.
     */
    @Override
    public void action() {
        action.doWork();
    }

    /* Item's children.
     *
     * @return List of item's
     * children if it has them.
     */
    @Override
    public List<Item> subItems() {
        return children;
    }

    /**
     * String representation of item.
     *
     * @return Item name as its string
     * representation.
     */
    @Override
    public String toString() {
        return itemName;
    }
}
