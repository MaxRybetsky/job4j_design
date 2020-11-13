package ru.job4j.io;

import java.util.*;
import java.util.function.Supplier;

public class Shell {
    private final Deque<String> dir = new ArrayDeque<>();
    private final Map<String, Supplier<Boolean>> disp = new HashMap<>();

    public Shell() {
        init();
    }

    public void cd(String path) {
        String[] folders = path.split("/");
        for (String folder : folders) {
            if (disp.containsKey(folder)) {
                disp.get(folder).get();
            } else {
                dir.add(folder);
            }
        }
    }

    public String pwd() {
        if (dir.isEmpty()) {
            return "/";
        }
        StringBuilder res = new StringBuilder();
        for (String folder : dir) {
            res.append("/");
            res.append(folder);
        }
        return res.toString();
    }

    private void init() {
        disp.put("", () -> false);
        disp.put("..", () -> {
            if (!dir.isEmpty()) {
                dir.removeLast();
            }
            return false;
        });
        disp.put(".", () -> false);
    }
}