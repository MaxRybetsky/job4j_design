package ru.job4j.it;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class FlatMap<T> implements Iterator<T> {
    private final Iterator<Iterator<T>> data;
    private Iterator<T> cursor;

    public FlatMap(Iterator<Iterator<T>> data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        return data.hasNext()
                || (cursor != null && cursor.hasNext());
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        if (cursor == null || !cursor.hasNext()) {
            cursor = data.next();
        }
        return cursor.next();
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

    public static void main(String[] args) {
        Integer[] aa = new Integer[]{};
        Iterator<Iterator<Integer>> a = Arrays.asList(
                Arrays.asList(aa).iterator()
        ).iterator();
        System.out.println(a.hasNext());
    }
}