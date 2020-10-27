package ru.job4j.io;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class Checker {
    private final Map<FileIdentifier, Path> filesMap;

    public Checker() {
        filesMap = new HashMap<>();
    }

    /**
     * Check if map {@code filesMap} already has this
     * {@code file}.
     *
     * @param file Path file to check
     * @return null if our map didn't contain file,
     * otherwise returns original file
     */
    public Path check(Path file) {
        FileIdentifier fileIdentifier = new FileIdentifier(file);
        if (filesMap.containsKey(fileIdentifier)) {
            return filesMap.get(fileIdentifier);
        }
        filesMap.put(fileIdentifier, file);
        return null;
    }
}
