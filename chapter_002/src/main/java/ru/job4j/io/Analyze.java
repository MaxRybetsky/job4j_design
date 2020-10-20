package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class Analyze {

    public Analyze(String logFile, List<String> log) {
        writeToFile(logFile, log);
    }

    public void unavailable(String source, String target) {
        List<String> resultList = new ArrayList<>();
        String regex = " ";
        String delimiter = ";";
        StringJoiner currString = new StringJoiner(delimiter);
        Iterator<String> iterator = readFile(source).iterator();
        boolean wasUnv = false;
        while (iterator.hasNext()) {
            String[] data = iterator.next().split(regex);
            if ("400".equals(data[0]) || "500".equals(data[0])) {
                if (!wasUnv) {
                    currString.add(data[1]);
                    wasUnv = true;
                }
                if(!iterator.hasNext()) {
                    resultList.add(currString.toString());
                }
            } else if ("200".equals(data[0]) || "300".equals(data[0])) {
                if (wasUnv) {
                    currString.add(data[1]);
                    resultList.add(currString.toString());
                    wasUnv = false;
                    currString = new StringJoiner(delimiter);
                }
            }
        }
        writeToFile(target, resultList);
    }

    public String getFileInfo(String path) {
        return readFile(path).toString();
    }

    private List<String> readFile(String path) {
        List<String> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(
                new FileReader(path)
        )) {
            list = reader.lines().collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    private void writeToFile(String file, List<String> list) {
        try (PrintWriter out = new PrintWriter(new FileOutputStream(file))) {
            list.forEach(e -> out.write(e + System.lineSeparator()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
