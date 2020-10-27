package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Application class for finding file
 * duplicates in root and subdirectories
 */
public class CopyFinderApp {

    /**
     * Starting looking for duplicates in
     * directories via {@code Files.walkFileTree}
     * method using {@code DirVisitor} class
     *
     * @param root Root directory to searching start
     * @param out  Output stream to get result of searching
     * @throws IOException if any IO errors will thrown
     */
    public static void findCopies(Path root, Output out)
            throws IOException {
        Files.walkFileTree(root, new DirVisitor(root, out));
    }
}
