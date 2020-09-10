package ru.job4j.collection;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class LinkListTest {
    @Test
    public void whenAddElems() {
        LinkList<Integer> list = new LinkList<>();
        list.add(12);
        list.add(15);
        list.add(120);
        assertThat(list.toString(), is("12 15 120 "));
    }

    @Test
    public void whenGetElem() {
        LinkList<Integer> list = new LinkList<>();
        list.add(12);
        list.add(15);
        list.add(120);
        assertThat(list.get(0), is(12));
        assertThat(list.get(1), is(15));
        assertThat(list.get(2), is(120));
    }

    @Test
    public void whenIteratorWorkes() {
        LinkList<Integer> list = new LinkList<>();
        list.add(12);
        list.add(15);
        list.add(120);
        Iterator<Integer> it = list.iterator();
        it.next();
        it.next();
        assertThat(it.next(), is(120));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetEmpty() {
        LinkList<Integer> list = new LinkList<>();
        list.get(0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenIndexOutOfBounds() {
        LinkList<Integer> list = new LinkList<>();
        list.add(12);
        list.get(1);
    }

    @Test(expected = NoSuchElementException.class)
    public void whenNoSuchElement() {
        LinkList<Integer> list = new LinkList<>();
        list.iterator().next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenIteratorThrowsException() {
        LinkList<Integer> list = new LinkList<>();
        list.add(12);
        list.add(15);
        list.add(120);
        Iterator<Integer> it = list.iterator();
        it.next();
        list.add(150);
        it.next();
    }
}