package ru.job4j.collection;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

public class SimpleArray<T> implements Iterable<T> {
    private T[] container;
    private final int numberOfElements = 10;
    private int index = 0;
    private int modCount = 0;

    @SuppressWarnings("unchecked")
    public SimpleArray(){
        container = (T[])new Object[numberOfElements];
    }

    public T get(int index) {
        return container[Objects.checkIndex(index, this.index)];
    }

    public void add(T model) {
        if(index == container.length - 1) {
            expContainer();
        }
        container[index++] = model;
        modCount++;
    }

    @Override
    public Iterator<T> iterator() {
        return new SimpleArrayIterator<>(this);
    }

    public int size() {
        return index;
    }

    public int getModCount() {
        return modCount;
    }

    @Override
    public String toString() {
        return Arrays.toString(Arrays.copyOf(container, index));
    }

    @SuppressWarnings("unchecked")
    private void expContainer(){
        T[] temp = container;
        container = (T[]) new Object[container.length + numberOfElements];
        System.arraycopy(temp, 0, container, 0, temp.length);
    }
}