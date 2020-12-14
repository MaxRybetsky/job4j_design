package ru.job4j.template;

import org.junit.Test;

import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class GeneratorTest {
    @Test
    public void whenAllOK() {
        String input = "I am ${name}, Who are ${subject}?";
        Map<String, String> map = Map.of(
                "name", "Max",
                "subject", "you"
        );
        Generator generator = new CustomGenerator();
        assertThat(
                generator.produce(input, map),
                is("I am Max, Who are you?")
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenNoEnoughKeys() {
        String input = "I am ${name}, Who are ${subject}?";
        Map<String, String> map = Map.of("name", "Max");
        Generator generator = new CustomGenerator();
        String result = generator.produce(input, map);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenTooManyKeys() {
        String input = "I am ${name}, Who are ${subject}?";
        Map<String, String> map = Map.of(
                "name", "Max",
                "subject", "you",
                "excessKey", "value"
        );
        Generator generator = new CustomGenerator();
        String result = generator.produce(input, map);
    }
}