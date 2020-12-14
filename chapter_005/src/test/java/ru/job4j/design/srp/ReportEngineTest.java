package ru.job4j.design.srp;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;

public class ReportEngineTest {
    private MemStore store;
    private ReportEngine engine;
    private Employee worker;

    @Before
    public void before() {
        store = new MemStore();
        Calendar now = Calendar.getInstance();
        worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        engine = new ReportEngine(store);
    }

    @Test
    public void whenOldGenerated() {
        String expect = "Name; Hired; Fired; Salary"
                + System.lineSeparator()
                + worker.getName() + ";"
                + worker.getHired() + ";"
                + worker.getFired() + ";"
                + worker.getSalary() + ";"
                + System.lineSeparator();
        assertThat(engine.generate(em -> true), is(expect));
    }

    @Test
    public void whenHtmlGenerated() {
        String expect = "<html><head><title>Report</title></head><body>"
                + "<p>Name; Hired; Fired; Salary<br>"
                + worker.getName() + ";"
                + worker.getHired() + ";"
                + worker.getFired() + ";"
                + worker.getSalary() + ";"
                + "<br>" + "</p>"
                + "</body></html>";
        assertThat(engine.generateHtml(em -> true), is(expect));
    }

    @Test
    public void whetReportForCounters() {
        String expect = "Name; Hired; Fired; Salary"
                + System.lineSeparator()
                + worker.getName() + ";"
                + worker.getHired() + ";"
                + worker.getFired() + ";"
                + "87.0" + ";"
                + System.lineSeparator();
        assertThat(engine.generateForCounters(em -> true), is(expect));
    }

    @Test
    public void whetReportForHR() {
        MemStore st = new MemStore();
        Employee e1 = new Employee("Frank", 1000);
        Employee e2 = new Employee("Ben", 1500);
        Employee e3 = new Employee("John", 1300);
        st.add(e1);
        st.add(e2);
        st.add(e3);
        ReportEngine eng = new ReportEngine(st);
        String expect = "Name; Salary"
                + System.lineSeparator()
                + e2.getName() + ";"
                + e2.getSalary() + ";"
                + System.lineSeparator()
                + e3.getName() + ";"
                + e3.getSalary() + ";"
                + System.lineSeparator()
                + e1.getName() + ";"
                + e1.getSalary() + ";"
                + System.lineSeparator();
        assertThat(eng.generateForHR(em -> true), is(expect));
    }
}