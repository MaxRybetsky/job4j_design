package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class LogFilter {
    public static List<String> filter(String file) {
        List<String> lines = new ArrayList<>();
        String filterValue = "404";
        for (String str : fileReader(file)) {
            if(filterValue.equals(str.split(" ")[8])) {
                lines.add(str);
            }
        }
        return lines;
    }

    public static List<String> fileReader(String file) {
        List<String> lines = new ArrayList<>();
        try(BufferedReader in = new BufferedReader(new FileReader("log.txt"))) {
            in.lines().forEach(lines::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lines;
    }

    public static void main(String[] args) {
        List<String> log = filter("log.txt");
        System.out.println(log);
    }
}
