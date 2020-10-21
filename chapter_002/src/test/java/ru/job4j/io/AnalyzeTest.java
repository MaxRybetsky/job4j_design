package ru.job4j.io;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AnalyzeTest {
    private final String source = "data/server.log";
    private final String target = "data/target.csv";

    @Test
    public void whenNotAvailableInMiddle() {
        Analyze analyze = new Analyze();
        writeToFile(source, List.of(
                "200 10:56:01",
                "200 10:57:01",
                "400 10:58:01",
                "500 11:01:02",
                "200 11:02:02",
                "200 11:03:02"
        ));
        analyze.unavailable(source, target);
        assertThat(
                getFileInfo(target),
                is("[10:58:01;11:02:02]"));
    }

    @Test
    public void whenNotAvailableInStart() {
        Analyze analyze = new Analyze();
        writeToFile(source, List.of(
                "400 10:54:01",
                "500 10:55:32",
                "200 10:56:01",
                "200 10:57:01"
        ));
        analyze.unavailable(source, target);
        assertThat(
                getFileInfo(target),
                is("[10:54:01;10:56:01]"));
    }

    @Test
    public void whenNotAvailableInEnd() {
        Analyze analyze = new Analyze();
        writeToFile(source, List.of(
                "200 10:56:01",
                "200 10:57:01",
                "400 10:58:01",
                "500 11:01:02"
        ));
        analyze.unavailable(source, target);
        assertThat(
                getFileInfo(target),
                is("[10:58:01]"));
    }

    @Test
    public void whenAlwaysAvailable() {
        Analyze analyze = new Analyze();
        writeToFile(source, List.of(
                "200 10:56:01",
                "200 10:57:01"
        ));
        analyze.unavailable(source, target);
        assertThat(
                getFileInfo(target),
                is("[]"));
    }

    @Test
    public void whenOneNotAvailableInEnd() {
        Analyze analyze = new Analyze();
        writeToFile(source, List.of(
                "200 10:56:01",
                "200 10:57:01",
                "500 11:01:02"
        ));
        analyze.unavailable(source, target);
        assertThat(
                getFileInfo(target),
                is("[11:01:02]"));
    }

    @Test
    public void whenNotAvailableInChaos() {
        Analyze analyze = new Analyze();
        writeToFile(source, List.of(
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
                getFileInfo(target),
                is(
                        "[10:54:01;10:56:01, "
                                + "10:58:01;11:02:02, "
                                + "11:04:01]"
                ));
    }

    @Test
    public void whenAlwaysNotAvailable() {
        Analyze analyze = new Analyze();
        writeToFile(source, List.of(
                "400 10:54:01",
                "500 10:55:02",
                "400 10:58:01",
                "500 11:01:02",
                "400 11:04:01",
                "500 11:05:02"
        ));
        analyze.unavailable(source, target);
        assertThat(
                getFileInfo(target),
                is("[10:54:01]"));
    }

    private String getFileInfo(String path) {
        List<String> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(
                new FileReader(path)
        )) {
            list = reader.lines().collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list.toString();
    }

    private void writeToFile(String file, List<String> list) {
        try (PrintWriter out = new PrintWriter(new FileOutputStream(file))) {
            list.forEach(e -> out.write(e + System.lineSeparator()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}