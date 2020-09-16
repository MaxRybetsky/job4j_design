package ru.job4j.map;

import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class UserTest {
    @Test
    public void whenCreateMap() {
        User u1 = new User("Max", 0,
                new GregorianCalendar(1996, Calendar.MARCH, 4));
        User u2 = new User("Max", 0,
                new GregorianCalendar(1996, Calendar.MARCH, 4));
        Map<User, Object> map = new HashMap<>();
        map.put(u1, new Object());
        map.put(u2, new Object());
        System.out.println(map);
    }
}