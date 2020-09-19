package ru.job4j.map;

import java.util.Objects;

public class BadHashClass {
    private int value;

    public BadHashClass(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BadHashClass that = (BadHashClass) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return value % 2 == 0 ? 0 : 1;
    }
}
