package ru.job4j.generics;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class RoleStoreTest {
    @Test
    public void whenAddElement() {
        Store<Role> roles = new RoleStore();
        Role role = new Role("0001", "admin");
        roles.add(role);
        assertThat(roles.findById(role.getId()), is(role));
    }

    @Test
    public void whenReplaceElement() {
        Store<Role> roles = new RoleStore();
        Role role = new Role("0001", "admin");
        Role newRole = new Role("0002", "moderator");
        roles.add(role);
        roles.replace(role.getId(), newRole);
        assertThat(roles.findById(newRole.getId()), is(newRole));
    }

    @Test
    public void whenDeleteElement() {
        Store<Role> roles = new RoleStore();
        Role role = new Role("0001", "admin");
        roles.add(role);
        roles.delete(role.getId());
        assertNull(roles.findById(role.getId()));
    }

    @Test
    public void whenFindElementById() {
        Store<Role> roles = new RoleStore();
        roles.add(new Role("0001", "admin"));
        roles.add(new Role("0002", "moderator"));
        roles.add(new Role("0003", "editor"));
        roles.add(new Role("0004", "user"));
        assertThat(roles.findById("0002").getNameOfRole(), is("moderator"));
    }
}