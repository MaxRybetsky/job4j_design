package ru.job4j.io;

import java.io.File;
import java.nio.file.Path;

/**
 * Class for identifying file in order to
 * check it for copies in Map data structure
 */
public class FileIdentifier {
    private final String name;
    private final long size;

    public FileIdentifier(Path file) {
        File f = file.toFile();
        name = f.getName();
        size = f.length();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FileIdentifier that = (FileIdentifier) o;
        return name.equals(that.name)
                && size == that.size;
    }

    @Override
    public int hashCode() {
        int result = Long.hashCode(size);
        result = 31 * result + name.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "FileIdentifier{"
                + "name='" + name + '\''
                + ", size=" + size
                + '}';
    }
}
