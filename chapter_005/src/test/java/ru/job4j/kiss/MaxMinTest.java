package ru.job4j.kiss;

import org.junit.Before;
import org.junit.Test;

import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class MaxMinTest {
    private List<Integer> integers;
    @Before
    public void before() {
        integers = List.of(5,9,10,34,1,45,0);
    }

    @Test
    public void whenMax() {
        assertThat(
                new MaxMin().max(integers, Comparator.comparingInt(o -> o)),
                is(45)
        );
    }

    @Test
    public void whenMin() {
        assertThat(
                new MaxMin().min(integers, Comparator.comparingInt(o -> o)),
                is(0)
        );
    }
}