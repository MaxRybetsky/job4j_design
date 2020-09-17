package ru.job4j.collection;

import java.util.Iterator;
import java.util.Objects;

public class SimpleSet<E> implements Iterable<E> {
    private final SimpleArray<E> array = new SimpleArray<>();

    public void add(E value) {
        for(E val : array) {
            if(Objects.equals(val, value)) {
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
