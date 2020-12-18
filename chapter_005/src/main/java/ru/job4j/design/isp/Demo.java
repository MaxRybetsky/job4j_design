package ru.job4j.design.isp;

import ru.job4j.design.isp.actions.Action;
import ru.job4j.design.isp.actions.SimpleAction;
import ru.job4j.design.isp.items.Item;
import ru.job4j.design.isp.items.MenuItem;

/**
 * Demo class.
 */
public class Demo {
    private Action action = new SimpleAction();

    private Item item111 = new MenuItem("item1.1.1", action);
    private Item item112 = new MenuItem("item1.1.2", action);
    private Item item11 = new MenuItem("item1.1", action, item111, item112);
    private Item item12 = new MenuItem("item1.2", action);
    private Item item1 = new MenuItem("item1", action, item11, item12);
    private Item item2 = new MenuItem("item2", action);

    private Menu menu = new Menu(item1);

    public static void main(String[] args) {
        Demo demo = new Demo();
        demo.menu.addItem(demo.item2);
        demo.menu.print();
    }
    /*
     * Output:
     *  item1
        --item1.1
        ----item1.1.1
        ----item1.1.2
        --item1.2
        item2
     */
}
