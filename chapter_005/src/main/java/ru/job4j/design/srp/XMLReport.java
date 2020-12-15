package ru.job4j.design.srp;

import java.io.StringWriter;
import java.util.function.Predicate;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 * Class for generating reports in XML.
 */
public class XMLReport extends AbstractReport {
    /**
     * Simple constructor to initialize
     * value of storage.
     *
     * @param store Source of employee's data.
     */
    public XMLReport(Store store) {
        super(store);
    }

    /**
     * Generates report in XML format with
     * employees info.
     *
     * @param filter Condition for choosing employees
     *               to add in report.
     * @return Report in XML format.
     */
    @Override
    public String generate(Predicate<Employee> filter) {
        EmployeesCollection collection = new EmployeesCollection(getStore().findBy(filter));
        return toXML(collection);
    }

    /**
     * Converts {@link EmployeesCollection} object
     * to XML in string format.
     *
     * @param employee Collection of {@link Employee}
     *                 objects.
     * @return XML representation of {@link Employee}
     *         objects in {@link EmployeesCollection}
     *          wrapper. If there were any process error
     *          returns empty string value.
     */
    private String toXML(EmployeesCollection employee) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(EmployeesCollection.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            StringWriter sw = new StringWriter();
            jaxbMarshaller.marshal(employee, sw);
            return sw.toString();
        } catch (JAXBException e) {
            e.printStackTrace();
            return "";
        }
    }
}
