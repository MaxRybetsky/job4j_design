package ru.job4j.design.srp;

import java.util.function.Predicate;

/**
 * Class for generating reports for
 * counters department.
 */
public class CountersDepartmentReport extends AbstractReport {
    /**
     * Simple constructor to initialize
     * value of storage.
     *
     * @param store Source of employee's data.
     */
    public CountersDepartmentReport(Store store) {
        super(store);
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
                    .append(setSalary(employee.getSalary())).append(";")
                    .append(System.lineSeparator());
        }
        return text.toString();
    }

    /**
     * Sets special value of salary.
     *
     * @param salary Input salary value.
     * @return Salary after editing.
     */
    private double setSalary(double salary) {
        return salary * 0.87;
    }

}
