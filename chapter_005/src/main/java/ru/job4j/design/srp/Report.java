package ru.job4j.design.srp;

import java.util.function.Predicate;

/**
 * Provides generate report function.
 */
public interface Report {
    /**
     * Generates report with
     * employees info
     *
     * @param filter Condition for choosing employees
     *               to add in report.
     * @return Report in String format.
     */
    String generate(Predicate<Employee> filter);
}
