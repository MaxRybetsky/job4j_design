package ru.job4j.design.srp;

public class TaxConverter implements Converter {
    /**
     * Current tax rate.
     */
    private int taxRate;

    /**
     * Sets new value of tax rate.
     *
     * @param taxRateInPercents new tax rate in percents.
     */
    public TaxConverter(int taxRateInPercents) {
        this.taxRate = taxRateInPercents;
    }

    /**
     * Counts size of salary after tax
     * paying.
     *
     * @param value Value to convert.
     * @return New value of salary without taxes.
     */
    @Override
    public double convert(double value) {
        return value * (100 - taxRate) / 100;
    }
}
