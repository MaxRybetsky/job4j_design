package ru.job4j.design.srp;

import java.util.List;
import java.util.function.Predicate;

/**
 * Class for getting different types
 * of reports.
 */
public class ReportEngine {
    /**
     * Storage of employee's data.
     */
    private Store store;

    /**
     * Simple constructor to initialize engine.
     *
     * @param store Source of employee's data.
     */
    public ReportEngine(Store store) {
        this.store = store;
    }

    /**
     * Generates standard report with
     * employees info.
     *
     * @param filter Condition for choosing employees
     *               to add in report.
     * @return Report in String format.
     */
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary")
                .append(System.lineSeparator());
        for (Employee employee : store.findBy(filter)) {
            text.append(employee.getName()).append(";")
                    .append(employee.getHired()).append(";")
                    .append(employee.getFired()).append(";")
                    .append(employee.getSalary()).append(";")
                    .append(System.lineSeparator());
        }
        return text.toString();
    }

    /**
     * Generates report in HTML format with
     * employees info.
     *
     * @param filter Condition for choosing employees
     *               to add in report.
     * @return Report in HTML format.
     */
    public String generateHtml(Predicate<Employee> filter) {
        return "<html><head><title>Report</title></head><body>"
                + "<p>"
                + generate(filter).replace(System.lineSeparator(), "<br>")
                + "</p>"
                + "</body></html>";
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
    public String generateForCounters(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary")
                .append(System.lineSeparator());
        for (Employee employee : store.findBy(filter)) {
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
     * @param salary Input salary value.
     * @return Salary after editing.
     */
    private double setSalary(double salary) {
        return salary * 0.87;
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
    public String generateForHR(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Salary")
                .append(System.lineSeparator());
        List<Employee> employees = store.findBy(filter);
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
