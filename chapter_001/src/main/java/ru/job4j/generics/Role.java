package ru.job4j.generics;

public class Role extends Base {
    private final String nameOfRole;

    public Role(String id, String nameOfRole) {
        super(id);
        this.nameOfRole = nameOfRole;
    }

    public String getNameOfRole() {
        return nameOfRole;
    }
}
