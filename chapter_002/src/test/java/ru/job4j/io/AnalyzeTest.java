package ru.job4j.io;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AnalyzeTest {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void whenNotAvailableInMiddle() throws IOException {
        File source = folder.newFile("server.log");
        File target = folder.newFile("target.csv");
        Analyze analyze = new Analyze();
        writeToFile(source, List.of(
                "200 10:56:01",
                "200 10:57:01",
                "400 10:58:01",
                "500 11:01:02",
                "200 11:02:02",
                "200 11:03:02"
        ));
        analyze.unavailable(source.getAbsolutePath(),
                target.getAbsolutePath());
        assertThat(
                getFileInfo(target),
                is("[10:58:01;11:02:02]"));
    }

    @Test
    public void whenNotAvailableInStart() throws IOException {
        File source = folder.newFile("server.log");
        File target = folder.newFile("target.csv");
        Analyze analyze = new Analyze();
        writeToFile(source, List.of(
                "400 10:54:01",
                "500 10:55:32",
                "200 10:56:01",
                "200 10:57:01"
        ));
        analyze.unavailable(source.getAbsolutePath(),
                target.getAbsolutePath());
        assertThat(
                getFileInfo(target),
                is("[10:54:01;10:56:01]"));
    }

    @Test
    public void whenNotAvailableInEnd() throws IOException {
        File source = folder.newFile("server.log");
        File target = folder.newFile("target.csv");
        Analyze analyze = new Analyze();
        writeToFile(source, List.of(
                "200 10:56:01",
                "200 10:57:01",
                "400 10:58:01",
                "500 11:01:02"
        ));
        analyze.unavailable(source.getAbsolutePath(),
                target.getAbsolutePath());
        assertThat(
                getFileInfo(target),
                is("[10:58:01]"));
    }

    @Test
    public void whenAlwaysAvailable() throws IOException {
        File source = folder.newFile("server.log");
        File target = folder.newFile("target.csv");
        Analyze analyze = new Analyze();
        writeToFile(source, List.of(
                "200 10:56:01",
                "200 10:57:01"
        ));
        analyze.unavailable(source.getAbsolutePath(),
                target.getAbsolutePath());
        assertThat(
                getFileInfo(target),
                is("[]"));
    }

    @Test
    public void whenOneNotAvailableInEnd() throws IOException {
        File source = folder.newFile("server.log");
        File target = folder.newFile("target.csv");
        Analyze analyze = new Analyze();
        writeToFile(source, List.of(
                "200 10:56:01",
                "200 10:57:01",
                "500 11:01:02"
        ));
        analyze.unavailable(source.getAbsolutePath(),
                target.getAbsolutePath());
        assertThat(
                getFileInfo(target),
                is("[11:01:02]"));
    }

    @Test
    public void whenNotAvailableInChaos() throws IOException {
        File source = folder.newFile("server.log");
        File target = folder.newFile("target.csv");
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
        analyze.unavailable(source.getAbsolutePath(),
                target.getAbsolutePath());
        assertThat(
                getFileInfo(target),
                is(
                        "[10:54:01;10:56:01, "
                                + "10:58:01;11:02:02, "
                                + "11:04:01]"
                ));
    }

    @Test
    public void whenAlwaysNotAvailable() throws IOException {
        File source = folder.newFile("server.log");
        File target = folder.newFile("target.csv");
        Analyze analyze = new Analyze();
        writeToFile(source, List.of(
                "400 10:54:01",
                "500 10:55:02",
                "400 10:58:01",
                "500 11:01:02",
                "400 11:04:01",
                "500 11:05:02"
        ));
        analyze.unavailable(source.getAbsolutePath(),
                target.getAbsolutePath());
        assertThat(
                getFileInfo(target),
                is("[10:54:01]"));
    }

    private String getFileInfo(File path) {
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

    private void writeToFile(File file, List<String> list) {
        try (PrintWriter out = new PrintWriter(new FileOutputStream(file))) {
            list.forEach(e -> out.write(e + System.lineSeparator()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}