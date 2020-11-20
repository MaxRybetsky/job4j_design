package ru.job4j.spammer;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ImportDB {

    private final Properties cfg;
    private final String dump;
    private boolean isTableExists = false;

    public ImportDB(Properties cfg, String dump) {
        this.cfg = cfg;
        this.dump = dump;
    }

    public List<User> load() throws IOException {
        List<User> users = new ArrayList<>();
        try (BufferedReader rd = new BufferedReader(new FileReader(dump))) {
            rd.lines().forEach(line -> {
                String[] data = line.split(";");
                users.add(new User(data[0], data[1]));
            });
        }
        return users;
    }

    public void save(List<User> users) throws ClassNotFoundException, SQLException {
        Class.forName(cfg.getProperty("jdbc.driver"));
        try (Connection cnt = DriverManager.getConnection(
                cfg.getProperty("jdbc.url"),
                cfg.getProperty("jdbc.username"),
                cfg.getProperty("jdbc.password")
        )) {
            if(!isTableExists) {
                setTable(cnt);
            }
            for (User user : users) {
                try (PreparedStatement ps = cnt.prepareStatement("insert into users"
                        + "(name, email) values(?,?) ")) {
                    ps.setString(1, user.name);
                    ps.setString(2, user.email);
                    ps.executeUpdate();
                }
            }
        }
    }

    private void setTable(Connection cn) throws SQLException {
        if (!isTableExists) {
            try (ResultSet resultSet = cn.getMetaData().
                    getTables(null,
                            null,
                            "users",
                            null)) {
                if (resultSet.next()) {
                    isTableExists = true;
                } else {
                    createTable(cn);
                }
            }
        }
    }

    private void createTable(Connection cn) {
        if (!isTableExists) {
            try (Statement stat = cn.createStatement()) {
                String creating = "create table users"
                        + "(id serial primary key,"
                        + " name varchar(200),"
                        + " email varchar(200));";
                stat.executeUpdate(creating);
                isTableExists = true;
            } catch (SQLException ex) {
                throw new IllegalStateException(ex);
            }
        }
    }

    private static class User {
        String name;
        String email;

        public User(String name, String email) {
            this.name = name;
            this.email = email;
        }
    }


    public static void main(String[] args) throws Exception {
        Properties cfg = new Properties();
        try (FileInputStream in = new FileInputStream("chapter_003/src/main/resources/app.properties")) {
            cfg.load(in);
        }
        ImportDB db = new ImportDB(cfg, "./dump.txt");
        db.save(db.load());
    }
}
