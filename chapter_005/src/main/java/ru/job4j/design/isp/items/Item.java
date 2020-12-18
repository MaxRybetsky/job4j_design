package ru.job4j.design.isp.items;

import java.util.List;

/**
 * Item of menu.
 */
public interface Item {
    /**
     * Name of item.
     *
     * @return Item's string name.
     */
    String name();

    /**
     * Item's action.
     */
    void action();

    /**
     * Item's children.
     *
     * @return List of item's
     * children if it has them.
     */
    List<Item> subItems();
}
