package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenNumbersIterator implements Iterator<Integer> {
    private final int[] data;
    private int point = 0;

    public EvenNumbersIterator(int[] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        point = point != -1 ? getEvenNumber() : -1;
        return point != -1;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[point++];
    }

    private int getEvenNumber() {
        for (int i = point; i < data.length; i++) {
            if (data[i] % 2 == 0) {
                return i;
            }
        }
        return -1;
    }
}