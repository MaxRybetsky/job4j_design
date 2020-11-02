package ru.job4j.serialization.json;

public class Person {
    private final String name;

    public Person(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{"
                + "name='" + name + '\''
                + '}';
    }

    public String getName() {
        return name;
    }
}
