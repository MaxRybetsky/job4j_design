package ru.job4j.generics;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayIterator<T> implements Iterator<T> {
    private final T[] data;
    private final int point;
    private int index = 0;

    public ArrayIterator(int point, T[] data) {
        this.point = point;
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        return index < point;
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[index++];
    }
}
