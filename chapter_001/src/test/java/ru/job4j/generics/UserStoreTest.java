package ru.job4j.generics;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class UserStoreTest {
    @Test
    public void whenAddElement() {
        Store<User> users = new UserStore();
        User user = new User("0001", "Max");
        users.add(user);
        assertThat(users.findById(user.getId()), is(user));
    }

    @Test
    public void whenReplaceElement() {
        Store<User> users = new UserStore();
        User user = new User("0001", "Max");
        User newUser = new User("0002", "Ivan");
        users.add(user);
        users.replace(user.getId(), newUser);
        assertThat(users.findById(newUser.getId()), is(newUser));
    }

    @Test
    public void whenDeleteElement() {
        Store<User> users = new UserStore();
        User user = new User("0001", "Max");
        users.add(user);
        users.delete(user.getId());
        assertNull(users.findById(user.getId()));
    }

    @Test
    public void whenFindElementById() {
        Store<User> users = new UserStore();
        users.add(new User("0001", "Max"));
        users.add(new User("0002", "Ivan"));
        users.add(new User("0003", "Egor"));
        users.add(new User("0004", "Vanya"));
        users.add(new User("0005", "Igor"));
        assertThat(users.findById("0003").getNickname(), is("Egor"));
    }
}