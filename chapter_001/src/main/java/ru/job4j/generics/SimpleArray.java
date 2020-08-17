package ru.job4j.generics;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

public class SimpleArray<T> implements Iterable<T> {
    private final T[] data;
    private int point = 0;

    /***
     * Constructor for initializing size of array {@code data}. Casting
     * {@code Object} to {@code T} type of array
     * @param size length of array {@code data}
     */
    @SuppressWarnings("unchecked")
    public SimpleArray(int size) {
        data = (T[]) new Object[size];
    }

    /***
     * Add element to {@code data} array
     * @param model element of array {@code data}
     * @throws IndexOutOfBoundsException if index is out of {@code data} array bounds
     */
    public void add(T model) throws IndexOutOfBoundsException {
        data[Objects.checkIndex(point++, data.length)] = model;
    }

    /***
     * Set element in specify place of array
     * @param index place of element setting at array
     * @param model element to set in array
     * @throws IndexOutOfBoundsException if index is out of {@code data} array bounds
     */
    public void set(int index, T model) throws IndexOutOfBoundsException {
        data[Objects.checkIndex(index, point)] = model;
    }

    /***
     * Remove element with {@code index} from array {@code data} and move elements to right
     * @param index element to delete
     * @throws IndexOutOfBoundsException if index is out of {@code data} array bounds
     */
    public void remove(int index) throws IndexOutOfBoundsException {
        index = Objects.checkIndex(index, point);
        System.arraycopy(data, index + 1, data, index, point - index - 1);
        point--;
    }

    /***
     * Get element from {@code data} with {@code index}
     * @param index of element
     * @return value of element with {@code index}
     * @throws IndexOutOfBoundsException if index is out of {@code data} array bounds
     */
    public T get(int index) throws IndexOutOfBoundsException {
        return data[Objects.checkIndex(index, point)];
    }

    /***
     * Iterator for foreach-type loops
     * @return Iterator object
     */
    @Override
    public Iterator<T> iterator() {
        return new ArrayIterator<>(point, data);
    }

    @Override
    public String toString() {
        return Arrays.toString(Arrays.copyOfRange(data, 0, point));
    }
}
