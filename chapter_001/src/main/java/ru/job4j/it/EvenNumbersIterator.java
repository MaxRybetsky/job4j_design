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
        return getEvenNumber(false) != -1;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return getEvenNumber(true);
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

    private int getEvenNumber(boolean changePoint) {
        for (int i = point; i < data.length; i++) {
            if (data[i] % 2 == 0) {
                if (changePoint) {
                    point = i + 1;
                }
                return data[i];
            }
        }
        return -1;
    }
}
