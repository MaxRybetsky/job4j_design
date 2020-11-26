package ru.job4j.io;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.IOException;
import java.nio.file.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class CopyFinderAppTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void whenOneCoincidence() throws IOException {
        Output out = new StringOut();
        folder.newFolder("a", "b", "c");
        folder.newFolder("d", "e", "f");
        folder.newFile("/a/b/c/file.txt");
        folder.newFile("/d/e/file.txt");
        Path root = folder.getRoot().toPath();
        CopyFinderApp.findCopies(root, out);
        assertThat(
                out.toString(),
                is(
                        "duplicate: d/e/file.txt\n"
                                + "original: a/b/c/file.txt\n"

                ));
    }

    @Test
    public void whenNoDuplicates() throws IOException {
        Output out = new StringOut();
        folder.newFolder("a", "b", "c");
        folder.newFolder("d", "e", "f");
        folder.newFile("/a/b/c/file.txt");
        folder.newFile("/d/e/file1.txt");
        Path root = folder.getRoot().toPath();
        CopyFinderApp.findCopies(root, out);
        System.out.println(out);
        assertThat(
                out.toString(),
                is("")
        );
    }

    @Test
    public void whenManyCoincidence() throws IOException {
        Output out = new StringOut();
        folder.newFolder("a", "b", "c");
        folder.newFolder("d", "e", "f");
        folder.newFolder("d", "g", "h", "i");
        folder.newFolder("j", "k", "l");
        folder.newFolder("m", "n", "o");
        folder.newFile("/a/b/c/file.txt");
        folder.newFile("/d/g/h/i/file.txt");
        folder.newFile("/j/file.txt");
        folder.newFile("/m/n/o/file.txt");
        Path root = folder.getRoot().toPath();
        CopyFinderApp.findCopies(root, out);
        assertThat(
                out.toString(),
                is(
                        "original: a/b/c/file.txt\n"
                                + "duplicate: d/g/h/i/file.txt\n"
                                + "original: a/b/c/file.txt\n"
                                + "duplicate: j/file.txt\n"
                                + "original: a/b/c/file.txt\n"
                                + "duplicate: m/n/o/file.txt\n"
                ));
    }
}