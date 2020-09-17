package ru.job4j.map;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Objects;

public class User {
    private final String name;
    private int children;
    private final Calendar birthday;

    public User() {
        name = "No name";
        children = 0;
        birthday = new GregorianCalendar(0, 0, 0);
    }

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, children, birthday);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        User user = (User) obj;
        return name.equals(user.name)
                && children == user.children
                && Objects.equals(birthday, user.birthday);
    }
}
