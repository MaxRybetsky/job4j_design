package ru.job4j.io;

import java.io.*;

public class Analyze {

    public void unavailable(String source, String target) {
        try (BufferedReader in = new BufferedReader(new FileReader(source));
             PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
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
                                out.write(currString.toString() + System.lineSeparator());
                                currString.setLength(0);
                            }
                        }
                    }
            );
            if (currString.length() != 0) {
                out.write(currString.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
