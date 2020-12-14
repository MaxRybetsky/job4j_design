package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;
import java.util.function.BiPredicate;

public class MaxMin {
    public <T> T max(List<T> value, Comparator<T> comparator) {
        return search(
                value,
                (max, elem) -> comparator.compare(max, elem) < 0
        );
    }


    public <T> T min(List<T> value, Comparator<T> comparator) {
        return search(
                value,
                (min, elem) -> comparator.compare(min, elem) > 0
        );
    }

    private <T> T search(List<T> value, BiPredicate<T, T> predicate) {
        if (isEmpty(value)) {
            return null;
        }
        T result = value.get(0);
        for (T elem : value) {
            if (predicate.test(result, elem)) {
                result = elem;
            }
        }
        return result;
    }

    private <T> boolean isEmpty(List<T> value) {
        return value == null || value.size() == 0;
    }
}
