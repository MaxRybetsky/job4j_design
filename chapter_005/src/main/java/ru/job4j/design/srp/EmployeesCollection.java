package ru.job4j.design.srp;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Help wrapper of {@link Employee} class
 * for keeping and converting collection
 * of {@link Employee} objects into
 * XML format of reports via JAXB library.
 */
@XmlRootElement
public class EmployeesCollection {
    private List<Employee> employees;

    public EmployeesCollection() {
        employees = new ArrayList<>();
    }

    public EmployeesCollection(List<Employee> employees) {
        this.employees = employees;
    }

    @XmlElement
    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
