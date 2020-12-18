package ru.job4j.design.isp.actions;

/**
 * Simple action.
 */
public class SimpleAction implements Action {
    /**
     * Simple work: printing string message.
     */
    @Override
    public void doWork() {
        System.out.println("It's simple action!");
    }
}
