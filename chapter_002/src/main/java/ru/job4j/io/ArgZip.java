package ru.job4j.io;

public class ArgZip {
    private final String[] args;
    private ArgsName argsName;

    public ArgZip(String[] args) {
        this.args = args;
        if (valid()) {
            argsName = ArgsName.of(new String[]{
                    args[0],
                    args[1],
                    args[2]
            });
        }
    }

    public boolean valid() {
        return args.length >= 3;
    }

    public String directory() {
        return argsName.get("d");
    }

    public String exclude() {
        return argsName.get("e");
    }

    public String output() {
        return argsName.get("o");
    }
}
