package ru.job4j.generics;

public class User extends Base{
    private final String nickname;

    protected User(String id, String nickname) {
        super(id);
        this.nickname = nickname;
    }

    public String getNickname() {
        return nickname;
    }
}
