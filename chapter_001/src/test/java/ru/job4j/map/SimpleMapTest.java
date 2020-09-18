package ru.job4j.map;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleMapTest {
    @Test
    public void whenAdd() {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        map.insert("Max", 1996);
        assertThat(map.toString(), is("[Max: 1996]\n"));
    }

    @Test
    public void whenGet() {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        map.insert("Max", 1996);
        assertThat(map.get("Max"), is(1996));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenGetBadKey() {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        map.insert("Max", 1996);
        int year = map.get("Ivan");
    }

    @Test
    public void whenDelete() {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        map.insert("Max", 1996);
        map.delete("Max");
        assertThat(map.iterator().hasNext(), is(false));
    }

    @Test
    public void whenDeleteBadKey() {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        map.insert("Max", 1996);
        map.delete("Ivan");
        assertThat(map.iterator().hasNext(), is(true));
    }

    @Test
    public void whenIterator() {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        map.insert("Max", 1996);
        map.insert("Vlad", 2000);
        map.insert("Kirill", 1980);
        Iterator<String> it = map.iterator();
        it.next();
        it.next();
        it.next();
        assertThat(it.hasNext(), is(false));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenIteratorHasNoElement() {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        map.insert("Max", 1996);
        map.insert("Vlad", 2000);
        Iterator<String> it = map.iterator();
        it.next();
        it.next();
        it.next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenConcurrentModification() {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        map.insert("Max", 1996);
        map.insert("Vlad", 2000);
        Iterator<String> it = map.iterator();
        it.next();
        map.insert("Ivan", 1989);
        it.next();
    }

    @Test
    public void whenResize() {
        SimpleMap<Integer, Integer> map = new SimpleMap<>();
        for (int i = 0; i <= 45; i++) {
            map.insert(i, i * i);
        }
        assertThat(map.getCapacity(), is(64));
    }
}