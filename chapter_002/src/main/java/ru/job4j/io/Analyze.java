package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Analyze {

    public void unavailable(String source, String target) {
        List<String> result = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(source))) {
            StringBuilder currString = new StringBuilder();
            in.lines().forEach(
                    e -> {
                        String[] data = e.split(" ");
                        if ("400".equals(data[0]) || "500".equals(data[0])) {
                            if (currString.length() == 0) {
                                currString.append(data[1]);
                            }
                        } else if ("200".equals(data[0]) || "300".equals(data[0])) {
                            if (currString.length() != 0) {
                                currString.append(";").append(data[1]);
                                result.add(currString.toString());
                                currString.setLength(0);
                            }
                        }
                    }
            );
            if (currString.length() != 0) {
                result.add(currString.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        writeToFile(target, result);
    }

    private void writeToFile(String file, List<String> list) {
        try (PrintWriter out = new PrintWriter(new FileOutputStream(file))) {
            list.forEach(e -> out.write(e + System.lineSeparator()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
