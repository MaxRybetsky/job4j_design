package ru.job4j.jdbc;

import java.io.*;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ConnectionDemo {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        String file = "chapter_003/data/app.properties";
        Map<String, String> loginData = getData(file);
        try (Connection connection = DriverManager.getConnection(loginData.get("url"),
                loginData.get("login"), loginData.get("password"))) {
            DatabaseMetaData data = connection.getMetaData();
            System.out.println(data.getUserName());
            System.out.println(data.getURL());
        }
    }

    public static Map<String, String> getData(String file) {
        Map<String, String> result = new HashMap<>();
        try (BufferedReader in = new BufferedReader(
                new FileReader(file))) {
            result = in.lines()
                        .collect(Collectors.toMap(
                                key -> key.split("=")[0],
                                value -> value.split("=")[1]
                        ));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


}
