package ru.job4j.design.srp;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;

public class ReportTest {
    private MemStore store;
    private Report engine;
    private Employee worker;

    @Before
    public void before() {
        store = new MemStore();
        Calendar date = Calendar.getInstance();
        date.set(2019, Calendar.OCTOBER, 11, 23, 45, 12);
        date.set(Calendar.MILLISECOND, 0);
        date.set(Calendar.DAY_OF_WEEK, 4);
        worker = new Employee("Ivan", date, date, 100);
        store.add(worker);
    }

    @Test
    public void whenOldGenerated() {
        engine = new ReportEngine(store);
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
        engine = new ProgrammersDepartmentReport(store);
        String expect = "<html><head><title>Report</title></head><body>"
                + "<p>Name; Hired; Fired; Salary<br>"
                + worker.getName() + ";"
                + worker.getHired() + ";"
                + worker.getFired() + ";"
                + worker.getSalary() + ";"
                + "<br>" + "</p>"
                + "</body></html>";
        assertThat(engine.generate(em -> true), is(expect));
    }

    @Test
    public void whetReportForCounters() {
        engine = new CountersDepartmentReport(store);
        String expect = "Name; Hired; Fired; Salary"
                + System.lineSeparator()
                + worker.getName() + ";"
                + worker.getHired() + ";"
                + worker.getFired() + ";"
                + "87.0" + ";"
                + System.lineSeparator();
        assertThat(engine.generate(em -> true), is(expect));
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
        Report eng = new HRDepartmentReport(st);
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
        assertThat(eng.generate(em -> true), is(expect));
    }

    @Test
    public void whenXMLReport() {
        engine = new XMLReport(store);
        String expect = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
                + "<employeesCollection>\n"
                + "    <employees>\n"
                + "        <fired>2019-10-16T23:45:12+03:00</fired>\n"
                + "        <hired>2019-10-16T23:45:12+03:00</hired>\n"
                + "        <name>" + worker.getName() + "</name>\n"
                + "        <salary>" + worker.getSalary() + "</salary>\n"
                + "    </employees>\n"
                + "</employeesCollection>\n";
        assertThat(engine.generate(em -> true), is(expect));
    }

    @Test
    public void whenJSONReport() {
        engine = new JSONReport(store);
        String expect = "[{"
                + "\"name\":\"Ivan\","
                + "\"hired\":"
                + "{\"year\":2019,\"month\":9,\"dayOfMonth\":16,\"hourOfDay\":23,"
                + "\"minute\":45,\"second\":12},"
                + "\"fired\":"
                + "{\"year\":2019,\"month\":9,\"dayOfMonth\":16,\"hourOfDay\":23,"
                + "\"minute\":45,\"second\":12},"
                + "\"salary\":100.0"
                + "}]";
        assertThat(engine.generate(em -> true), is(expect));
    }
}