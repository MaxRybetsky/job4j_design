package ru.job4j.generics;

import java.util.Arrays;
import java.util.Objects;

public class SimpleArray<T> {
    private final T[] data;
    private int point = 0;

    @SuppressWarnings("unchecked")
    public SimpleArray(int size) {
        data = (T[]) new Object[size];
    }

    public boolean add(T model) {

        data[point++] = model;
    }

    @Override
    public String toString() {
        return Arrays.toString(data);
    }

    public static void main(String[] args) {
        SimpleArray<Integer> ar = new SimpleArray<>(20);
        for (int i = 0; i < 20; i++) {
            ar.add(i);
        }
        System.out.println(ar);
    }
}
