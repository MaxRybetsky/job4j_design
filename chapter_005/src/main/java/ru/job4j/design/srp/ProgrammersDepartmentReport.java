package ru.job4j.design.srp;

import java.util.function.Predicate;

/**
 * Class for generating reports for
 * programmers department.
 */
public class ProgrammersDepartmentReport extends AbstractReport {
    /**
     * Simple constructor to initialize
     * value of storage.
     *
     * @param store Source of employee's data.
     */
    public ProgrammersDepartmentReport(Store store) {
        super(store);
    }

    /**
     * Generates report in HTML format with
     * employees info.
     *
     * @param filter Condition for choosing employees
     *               to add in report.
     * @return Report in HTML format.
     */
    @Override
    public String generate(Predicate<Employee> filter) {
        Report report = new ReportEngine(getStore());
        return "<html><head><title>Report</title></head><body>"
                + "<p>"
                + report.generate(filter).replace(System.lineSeparator(), "<br>")
                + "</p>"
                + "</body></html>";
    }
}
