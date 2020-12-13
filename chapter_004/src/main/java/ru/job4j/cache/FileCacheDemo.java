package ru.job4j.cache;

import java.io.IOException;

public class FileCacheDemo {
    public static void main(String[] args) {
        String directory = "chapter_004/files";
        try {
            FileCache fileCache = new FileCache(directory);
            System.out.println(fileCache.getFileContent("test1.txt"));
            System.out.println(fileCache.getFileContent("test2.txt"));
            System.out.println(fileCache.getFileContent("test3.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
