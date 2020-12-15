package ru.job4j.design.srp;

/**
 * Abstract class of reports.
 * Keeps info about source of reports data.
 * Implements {@link Report} interface to
 * provide access to generate report.
 */
public abstract class AbstractReport implements Report {
    /**
     * Storage of employee's data.
     */
    private Store store;

    /**
     * Simple constructor to initialize
     * value of storage.
     *
     * @param store Source of employee's data.
     */
    public AbstractReport(Store store) {
        this.store = store;
    }

    /**
     * Returns storage value.
     *
     * @return Value of store.
     */
    public Store getStore() {
        return store;
    }
}
