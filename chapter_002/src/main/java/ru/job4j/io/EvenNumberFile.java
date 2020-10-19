package ru.job4j.io;

import java.io.FileInputStream;

public class EvenNumberFile {
    public static void main(String[] args) {
        evenCheckAndPrint(strToNumbers(readNumbers()));
    }

    public static void evenCheckAndPrint(int[] array) {
        for (int value : array) {
            System.out.println(value + " is " +
                    (value % 2 == 0 ? "even" : "odd"));
        }
    }

    public static int[] strToNumbers(StringBuilder str) {
        String[] strs = str.toString().split(System.lineSeparator());
        int[] result = new int[strs.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = Integer.parseInt(strs[i]);
        }
        return result;
    }

    public static StringBuilder readNumbers() {
        StringBuilder str = new StringBuilder();
        try (FileInputStream in = new FileInputStream("even.txt")) {
            int read;
            while ((read = in.read()) != -1) {
                str.append((char) read);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }
}
