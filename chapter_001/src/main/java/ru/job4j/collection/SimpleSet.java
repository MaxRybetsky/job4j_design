package ru.job4j.collection;

import java.util.Iterator;

public class SimpleSet<E extends Comparable<E>> implements Iterable<E> {
    private final SimpleArray<E> array = new SimpleArray<>();

    public void add(E value) {
        for(E val : array) {
            if(val.compareTo(value) == 0) {
                return;
            }
        }
        array.add(value);
    }

    @Override
    public Iterator<E> iterator() {
        return array.iterator();
    }
}
