package ru.job4j.io;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

import java.util.List;

public class AnalyzeTest {
    private final String source = "data/server.log";
    private final String target = "data/target.csv";

    @Test
    public void whenNotAvailableInMiddle() {
        Analyze analyze = new Analyze(source, List.of(
                "200 10:56:01",
                "200 10:57:01",
                "400 10:58:01",
                "500 11:01:02",
                "200 11:02:02",
                "200 11:03:02"
        ));
        analyze.unavailable(source, target);
        assertThat(
                analyze.getFileInfo(target),
                is("[10:58:01;11:02:02]"));
    }

    @Test
    public void whenNotAvailableInStart() {
        Analyze analyze = new Analyze(source, List.of(
                "400 10:54:01",
                "500 10:55:32",
                "200 10:56:01",
                "200 10:57:01"
        ));
        analyze.unavailable(source, target);
        assertThat(
                analyze.getFileInfo(target),
                is("[10:54:01;10:56:01]"));
    }

    @Test
    public void whenNotAvailableInEnd() {
        Analyze analyze = new Analyze(source, List.of(
                "200 10:56:01",
                "200 10:57:01",
                "400 10:58:01",
                "500 11:01:02"
        ));
        analyze.unavailable(source, target);
        assertThat(
                analyze.getFileInfo(target),
                is("[10:58:01]"));
    }

    @Test
    public void whenAlwaysAvailable() {
        Analyze analyze = new Analyze(source, List.of(
                "200 10:56:01",
                "200 10:57:01"
        ));
        analyze.unavailable(source, target);
        assertThat(
                analyze.getFileInfo(target),
                is("[]"));
    }

    @Test
    public void whenOneNotAvailableInEnd() {
        Analyze analyze = new Analyze(source, List.of(
                "200 10:56:01",
                "200 10:57:01",
                "500 11:01:02"
        ));
        analyze.unavailable(source, target);
        assertThat(
                analyze.getFileInfo(target),
                is("[11:01:02]"));
    }

    @Test
    public void whenNotAvailableInChaos() {
        Analyze analyze = new Analyze(source, List.of(
                "400 10:54:01",
                "500 10:55:02",
                "200 10:56:01",
                "200 10:57:01",
                "400 10:58:01",
                "500 11:01:02",
                "200 11:02:02",
                "200 11:03:02",
                "400 11:04:01",
                "500 11:05:02"
        ));
        analyze.unavailable(source, target);
        assertThat(
                analyze.getFileInfo(target),
                is(
                        "[10:54:01;10:56:01, "
                                + "10:58:01;11:02:02, "
                                + "11:04:01]"
                ));
    }

    @Test
    public void whenAlwaysNotAvailable() {
        Analyze analyze = new Analyze(source, List.of(
                "400 10:54:01",
                "500 10:55:02",
                "400 10:58:01",
                "500 11:01:02",
                "400 11:04:01",
                "500 11:05:02"
        ));
        analyze.unavailable(source, target);
        assertThat(
                analyze.getFileInfo(target),
                is("[10:54:01]"));
    }
}