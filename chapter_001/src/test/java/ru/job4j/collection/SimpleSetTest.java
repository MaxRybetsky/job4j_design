package ru.job4j.collection;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleSetTest {
    @Test
    public void whenAddElements(){
        SimpleSet<Integer> set = new SimpleSet<>();
        set.add(1);
        set.add(2);
        Iterator<Integer> it = set.iterator();
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
    }

    @Test
    public void whenAddDuplicateElements(){
        SimpleSet<Integer> set = new SimpleSet<>();
        set.add(1);
        set.add(2);
        set.add(1);
        set.add(2);
        Iterator<Integer> it = set.iterator();
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
        assertThat(it.hasNext(), is(false));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenNoElems(){
        SimpleSet<Integer> set = new SimpleSet<>();
        set.iterator().next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenIteratorAndModification(){
        SimpleSet<Integer> set = new SimpleSet<>();
        set.add(1);
        set.add(2);
        Iterator<Integer> it = set.iterator();
        set.add(3);
        set.add(4);
        it.next();
    }
}