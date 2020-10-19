package ru.job4j.exam;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class AnalyzeTest {

    @Test
    public void whenAllAdded() {
        Analyze analyze = new Analyze();
        assertThat(
                analyze.diff(
                        List.of(),
                        List.of(
                                new User(1, "Max"),
                                new User(2, "Ivan"),
                                new User(3, "Vasya")
                        )
                ),
                is(new Info(3, 0, 0)));
    }

    @Test
    public void whenAllDeleted() {
        Analyze analyze = new Analyze();
        assertThat(
                analyze.diff(
                        List.of(
                                new User(1, "Max"),
                                new User(2, "Ivan"),
                                new User(3, "Vasya")
                        ),
                        List.of()
                ),
                is(new Info(0, 0, 3)));
    }

    @Test
    public void whenPrevAndCurrAreEmpty() {
        Analyze analyze = new Analyze();
        assertThat(
                analyze.diff(
                        List.of(),
                        List.of()
                ),
                is(new Info(0, 0, 0)));
    }

    @Test
    public void whenNoChange() {
        Analyze analyze = new Analyze();
        assertThat(
                analyze.diff(
                        List.of(
                                new User(1, "Max"),
                                new User(2, "Ivan"),
                                new User(3, "Vasya")
                        ),
                        List.of(
                                new User(1, "Max"),
                                new User(2, "Ivan"),
                                new User(3, "Vasya")
                        )
                ),
                is(new Info(0, 0, 0)));
    }

    @Test
    public void when2ElemsChanged() {
        Analyze analyze = new Analyze();
        assertThat(
                analyze.diff(
                        List.of(
                                new User(1, "Max"),
                                new User(2, "Ivan"),
                                new User(3, "Vasya")
                        ),
                        List.of(
                                new User(1, "Max"),
                                new User(2, "Vanya"),
                                new User(3, "Vasiliy")
                        )
                ),
                is(new Info(0, 2, 0)));
    }

    @Test
    public void whenAllChanged() {
        Analyze analyze = new Analyze();
        assertThat(
                analyze.diff(
                        List.of(
                                new User(1, "Max"),
                                new User(2, "Ivan"),
                                new User(3, "Vasya")
                        ),
                        List.of(
                                new User(1, "Maxim"),
                                new User(2, "Vanchik"),
                                new User(3, "Vasyok")
                        )
                ),
                is(new Info(0, 3, 0)));
    }

    @Test
    public void when3Added3Changed3Deleted() {
        Analyze analyze = new Analyze();
        assertThat(
                analyze.diff(
                        List.of(
                                new User(1, "Max"),
                                new User(2, "Ivan"),
                                new User(3, "Vasya"),
                                new User(4, "Petr"),
                                new User(5, "Andrey"),
                                new User(6, "Sergey"),
                                new User(7, "Stanislav"),
                                new User(8, "Boris"),
                                new User(9, "Roman"),
                                new User(10, "Vyacheslav"),
                                new User(11, "Vladimir"),
                                new User(12, "Michael")
                        ),
                        List.of(
                                new User(1, "Maxim"),
                                new User(4, "Petr"),
                                new User(5, "Andrey"),
                                new User(6, "Sergey"),
                                new User(7, "Stanislav"),
                                new User(8, "Borya"),
                                new User(9, "Roma"),
                                new User(10, "Vyacheslav"),
                                new User(12, "Michael"),
                                new User(13, "Pavel"),
                                new User(14, "Nikolay"),
                                new User(15, "Nail")
                        )
                ),
                is(new Info(3, 3, 3)));
    }

    /*@Test
    public void when() {

    }*/

}