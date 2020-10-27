package ru.job4j.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

public class MainZip {
    public static void main(String[] args) throws IOException {
        ArgZip az = new ArgZip(args);
        Zip zip = new Zip(Paths.get(az.directory()));
        zip.packFiles(
                zip.fileFilter(az.exclude()),
                new File(az.output())
        );
    }

}
