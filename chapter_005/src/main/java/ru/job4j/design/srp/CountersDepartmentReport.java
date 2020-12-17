package ru.job4j.design.srp;

import java.util.function.Predicate;

/**
 * Class for generating reports for
 * counters department.
 */
public class CountersDepartmentReport extends AbstractReport {
    /**
     * Salary converter.
     */
    private Converter converter;

    /**
     * Simple constructor to initialize
     * value of storage.
     *
     * @param store Source of employee's data.
     * @param converter Custom salary-converter.
     */
    public CountersDepartmentReport(Store store, Converter converter) {
        super(store);
        this.converter = converter;
    }

    /**
     * Generates report in String format with
     * employees info. Sets value of salary after
     * editing in special method.
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
                    .append(converter.convert(employee.getSalary())).append(";")
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
