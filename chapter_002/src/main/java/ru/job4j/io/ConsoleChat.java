package ru.job4j.io;

import java.io.*;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleChat {
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        StringBuilder dialog = new StringBuilder();
        String userMessage;
        int commandID = 1;
        CommandsDisp disp = new CommandsDisp();
        while (commandID != 3) {
            System.out.print("user: ");
            userMessage = sc.nextLine();
            commandID = disp.getCommandID(userMessage);
            dialog.append("user: ")
                    .append(userMessage)
                    .append(System.lineSeparator());
            if (commandID == 1) {
                String botMessage = "bot:  " + botAnswer();
                System.out.println(botMessage);
                dialog.append(botMessage)
                        .append(System.lineSeparator());
            }
        }
        writeFile(dialog.toString(), path);
    }

    private String botAnswer() {
        List<String> answers = readFile(botAnswers);
        int ansPos = (int) (Math.random() * answers.size());
        return answers.get(ansPos);
    }

    private List<String> readFile(String path) {
        List<String> result = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(
                new FileReader(path, StandardCharsets.UTF_8))) {
            in.lines().forEach(result::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    private void writeFile(String data, String path) {
        try (BufferedWriter out = new BufferedWriter(
                new FileWriter(path, StandardCharsets.UTF_8))) {
            out.write(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("logConsoleChat.txt", "bot.txt");
        cc.run();
    }
}
