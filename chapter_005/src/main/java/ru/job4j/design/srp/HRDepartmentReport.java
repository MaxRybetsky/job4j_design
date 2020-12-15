package ru.job4j.design.srp;

import java.util.List;
import java.util.function.Predicate;

/**
 * Class for generating reports for
 * HR department.
 */
public class HRDepartmentReport extends AbstractReport {
    /**
     * Simple constructor to initialize
     * value of storage.
     *
     * @param store Source of employee's data.
     */
    public HRDepartmentReport(Store store) {
        super(store);
    }

    /**
     * Generates report in String format with
     * employees name and salary info unordered
     * by salary.
     *
     * @param filter Condition for choosing employees
     *               to add in report.
     * @return Report in String format.
     */
    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Salary")
                .append(System.lineSeparator());
        List<Employee> employees = getStore().findBy(filter);
        employees.sort((o1, o2) -> {
            if (o1.getSalary() == o2.getSalary()) {
                return 0;
            }
            return o1.getSalary() > o2.getSalary() ? -1 : 1;
        });
        for (Employee employee : employees) {
            text.append(employee.getName()).append(";")
                    .append(employee.getSalary()).append(";")
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
