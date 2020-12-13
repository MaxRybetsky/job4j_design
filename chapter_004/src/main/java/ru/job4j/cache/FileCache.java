package ru.job4j.cache;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * Simple file cache class for
 * temporary storing files content
 * in cache.
 */
public class FileCache {
    /**
     * Main cache.
     */
    private final Cache<String> cache;

    /**
     * Target file's extension.
     * Default value is ".txt".
     */
    private String extension;

    /**
     * Directory to search files.
     * Default value is "./".
     */
    private String directory;

    /**
     * Default constructor.
     *
     * @throws IOException if smth went wrong.
     */
    public FileCache() throws IOException {
        this("./", ".txt");
    }

    /**
     * Constructor with a given directory
     * value and default value of extension.
     *
     * @param directory Directory to search files.
     * @throws IOException if smth went wrong.
     */
    public FileCache(String directory) throws IOException {
        this(directory, ".txt");
    }

    /**
     * Constructor with a given values of
     * directory and extension.
     *
     * @param directory Directory to search files.
     * @param extension Target file's extension.
     * @throws IOException if smth went wrong.
     */
    public FileCache(String directory, String extension) throws IOException {
        this.cache = new Cache<>();
        this.extension = extension;
        addFilesToCache(directory);
    }

    /**
     * Gets target file's extension.
     *
     * @return File's extension.
     */
    public String getExtension() {
        return extension;
    }

    /**
     * Sets new value of target file's
     * extension.
     *
     * @param extension New value of extension.
     */
    public void setExtension(String extension) {
        this.extension = extension;
    }

    /**
     * Gets directory to search value.
     *
     * @return Directory value.
     */
    public String getDirectory() {
        return directory;
    }

    /**
     * Sets new value of directory to search.
     *
     * @param directory New directory to search
     *                  value.
     */
    public void setDirectory(String directory) {
        this.directory = directory;
    }

    /**
     * Adds files with the current extension value
     * from the given directory to cache.
     * Sets new directory value, go trough it and add
     * all valid-by-extension files to cache.
     *
     * @param directory Given directory to search.
     * @throws IOException if smth went wrong.
     */
    public void addFilesToCache(String directory) throws IOException {
        setDirectory(directory);
        Files.walkFileTree(Path.of(directory), new FileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                if (fileHasRequiredExtension(file)) {
                    addFileToCache(file);
                }
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                return FileVisitResult.SKIP_SUBTREE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                return FileVisitResult.CONTINUE;
            }
        });
    }

    /**
     * Checks if the given file has
     * required extension.
     *
     * @param file File to check.
     * @return {true} if file has required extension,
     * otherwise - {@code false}.
     */
    private boolean fileHasRequiredExtension(Path file) {
        if (file == null) {
            return false;
        }
        return file.getFileName().toString().endsWith(extension);
    }

    /**
     * Adds the given file to cache.
     *
     * @param file File to add.
     * @throws IOException if smth went wrong.
     */
    private void addFileToCache(Path file) throws IOException {
        if (file == null) {
            return;
        }
        String fileName = file.getFileName().toString();
        String content = readFile(file);
        cache.add(fileName, content);
    }

    /**
     * Reads file's content and returns its value
     * in string format.
     *
     * @param file File to read.
     * @return String representation of file's content.
     * @throws IOException if smth went wrong.
     */
    private String readFile(Path file) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(file.toFile()))) {
            StringBuilder result = new StringBuilder();
            reader.lines().forEach(line -> result.append(line).append("\n"));
            return result.toString();
        } catch (IOException e) {
            throw new IOException(e);
        }
    }

    /**
     * Returns file's content from cache with
     * the given file name. If there is null
     * value of content with the given file
     * name, method adds this file's content
     * to cache again.
     *
     * @param fileName Name of file to get content.
     * @return String representation of file content
     * in cache
     * @throws IOException if smth went wrong.
     */
    public String getFileContent(String fileName) throws IOException {
        if (cache.get(fileName) == null) {
            addFileToCache(Paths.get(directory, fileName));
        }
        return cache.get(fileName);
    }
}
