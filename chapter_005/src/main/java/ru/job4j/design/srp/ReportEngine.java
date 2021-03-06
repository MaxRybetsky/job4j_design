package ru.job4j.design.srp;

import java.util.function.Predicate;

/**
 * Class for getting standard type
 * of report.
 */
public class ReportEngine extends AbstractReport {
    /**
     * Simple constructor to initialize
     * value of storage.
     *
     * @param store Source of employee's data.
     */
    public ReportEngine(Store store) {
        super(store);
    }

    /**
     * Generates standard report with
     * employees info.
     *
     * @param filter Condition for choosing employees
     *               to add in report.
     * @return Report in String format.
     */
    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary")
                .append(System.lineSeparator());
        for (Employee employee : getStore().findBy(filter)) {
            text.append(employee.getName()).append(";")
                    .append(employee.getHired()).append(";")
                    .append(employee.getFired()).append(";")
                    .append(employee.getSalary()).append(";")
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
