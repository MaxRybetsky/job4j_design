package ru.job4j.generics;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleArrayTest {
    @Test
    public void whenAdd() {
        SimpleArray<String> strings = new SimpleArray<>(6);
        strings.add("j");
        strings.add("a");
        strings.add("v");
        strings.add("a");
        assertThat(strings.toString(), is("[j, a, v, a]"));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddAndOutOfBounds() {
        SimpleArray<String> strings = new SimpleArray<>(2);
        strings.add("ja");
        strings.add("va");
        strings.add("ja");
        strings.add("va");
    }

    @Test
    public void whenRemove1() {
        SimpleArray<Integer> ints = new SimpleArray<>(6);
        ints.add(1);
        ints.add(1);
        ints.add(2);
        ints.add(3);
        ints.add(5);
        ints.remove(2);
        assertThat(ints.toString(), is("[1, 1, 3, 5]"));
    }

    @Test
    public void whenRemove2() {
        SimpleArray<Integer> ints = new SimpleArray<>(6);
        ints.add(1);
        ints.add(1);
        ints.add(2);
        ints.add(3);
        ints.add(5);
        ints.remove(2);
        ints.remove(2);
        assertThat(ints.toString(), is("[1, 1, 5]"));
    }

    @Test
    public void whenRemove3() {
        SimpleArray<Integer> ints = new SimpleArray<>(6);
        ints.add(1);
        ints.add(1);
        ints.add(2);
        ints.add(3);
        ints.add(5);
        ints.remove(2);
        ints.remove(2);
        ints.remove(2);
        assertThat(ints.toString(), is("[1, 1]"));
    }

    @Test
    public void whenRemove4() {
        SimpleArray<Integer> ints = new SimpleArray<>(6);
        ints.add(1);
        ints.add(1);
        ints.add(2);
        ints.add(3);
        ints.add(5);
        ints.add(8);
        ints.remove(4);
        ints.remove(0);
        ints.remove(2);
        assertThat(ints.toString(), is("[1, 2, 8]"));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenRemoveAndOutOfBounds() {
        SimpleArray<Integer> ints = new SimpleArray<>(6);
        ints.add(1);
        ints.add(2);
        ints.add(3);
        ints.remove(3);
    }

    @Test
    public void whenSet() {
        SimpleArray<Double> doubles = new SimpleArray<>(6);
        doubles.add(0.0);
        doubles.add(3.0);
        doubles.add(5.0);
        doubles.add(9.0);
        doubles.add(12.0);
        doubles.set(2, 6.0);
        assertThat(doubles.toString(), is("[0.0, 3.0, 6.0, 9.0, 12.0]"));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenSetAndOutOfBounds() {
        SimpleArray<Double> doubles = new SimpleArray<>(7);
        doubles.add(0.0);
        doubles.add(3.0);
        doubles.add(6.0);
        doubles.add(9.0);
        doubles.add(12.0);
        doubles.set(6, 15.0);
    }

    @Test
    public void whenGet() {
        SimpleArray<Character> characters = new SimpleArray<>(4);
        characters.add('j');
        characters.add('a');
        characters.add('v');
        characters.add('a');
        assertThat(characters.get(2), is('v'));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetAndOutOfBounds() {
        SimpleArray<Character> characters = new SimpleArray<>(7);
        characters.add('j');
        characters.add('a');
        characters.add('v');
        characters.add('a');
        Character character = characters.get(5);
    }

    @Test
    public void whenForEachLoop() {
        SimpleArray<Integer> ints = new SimpleArray<>(20);
        for (int i = 0; i <= 10; i++) {
            ints.add(i * 2);
        }
        StringBuilder res = new StringBuilder();
        for (int value : ints) {
            res.append(value);
        }
        assertThat(res.toString(), is("02468101214161820"));
    }
}