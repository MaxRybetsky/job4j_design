package ru.job4j.design.srp;

/**
 * Simple converter.
 */
public interface Converter {
    /**
     * Converts value to new value
     * with the indicated changes
     *
     * @param value Value to convert.
     * @return New value after converting.
     */
    double convert(double value);
}
