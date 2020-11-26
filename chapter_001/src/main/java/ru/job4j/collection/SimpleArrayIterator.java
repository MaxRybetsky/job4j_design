package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleArrayIterator<T> implements Iterator<T> {
    private final SimpleArray<T> array;
    private int index = 0;
    private final int expectedModCount;

    public SimpleArrayIterator(SimpleArray<T> array) {
        this.array = array;
        this.expectedModCount = array.getModCount();
    }

    @Override
    public boolean hasNext() {
        if (isModified()) {
            throw new ConcurrentModificationException();
        }
        return index < array.size();
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return array.get(index++);
    }

    private boolean isModified() {
        return array.getModCount() != expectedModCount;
    }
}
