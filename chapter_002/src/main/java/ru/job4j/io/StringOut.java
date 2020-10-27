package ru.job4j.io;

public class StringOut implements Output {
    private final StringBuilder buffer;

    public StringOut() {
        buffer = new StringBuilder();
    }

    @Override
    public void println(Object obj) {
        if (obj == null) {
            buffer.append("null");
        } else {
            buffer.append(obj.toString());
        }
        buffer.append(System.lineSeparator());
    }

    @Override
    public String toString() {
        return buffer.toString();
    }
}
