package ru.job4j.design.srp;

import com.google.gson.GsonBuilder;

import java.util.function.Predicate;

/**
 * Class for generating reports in JSON.
 */
public class JSONReport extends AbstractReport {
    /**
     * Simple constructor to initialize
     * value of storage.
     *
     * @param store Source of employee's data.
     */
    public JSONReport(Store store) {
        super(store);
    }

    /**
     * Generates report in JSON format with
     * employees info.
     *
     * @param filter Condition for choosing employees
     *               to add in report.
     * @return Report in JSON format.
     */
    @Override
    public String generate(Predicate<Employee> filter) {
        return new GsonBuilder().create().toJson(getStore().findBy(filter));
    }
}
