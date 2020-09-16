package ru.job4j.map;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class User {
    private final String name;
    private int children;
    private final Calendar birthday;

    public User(){
        name = "No name";
        children = 0;
        birthday = new GregorianCalendar(0, 0, 0);
    }

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }
}
