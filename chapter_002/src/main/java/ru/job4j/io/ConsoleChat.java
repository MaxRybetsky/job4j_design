package ru.job4j.io;

import java.io.*;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class ConsoleChat {
    private final String path;
    private final String botAnswers;
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        String userMessage;
        boolean wasBreak = false;
        try (BufferedWriter out = new BufferedWriter(
                new FileWriter(path, StandardCharsets.UTF_8, true)
        )) {
            System.out.print("user: ");
            while (!OUT.equals(userMessage = sc.nextLine())) {
                out.write("user: " + userMessage + System.lineSeparator());
                if (STOP.equals(userMessage)) {
                    wasBreak = true;
                }
                if (CONTINUE.equals(userMessage)) {
                    wasBreak = false;
                }
                if (!wasBreak) {
                    String botMessage = "bot:  " + botAnswer();
                    System.out.println(botMessage);
                    out.write(botMessage + System.lineSeparator());
                }
                System.out.print("user: ");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String botAnswer() {
        try (RandomAccessFile file =
                     new RandomAccessFile(botAnswers, "r")) {
            long fileLength = file.length();
            long pos = (long) (Math.random() * fileLength);
            while (pos >= 0 && file.readByte() != 13) {
                file.seek(pos--);
            }
            file.readLine();
            return new String(file.readLine()
                    .getBytes(StandardCharsets.ISO_8859_1),
                    StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
            return "Sorry, error happens";
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("logConsoleChat.txt", "bot.txt");
        cc.run();
    }
}
