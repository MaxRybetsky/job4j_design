package ru.job4j.gc;

import static com.carrotsearch.sizeof.RamUsageEstimator.sizeOf;

/**
 * When all fields are empty this object takes
 * 32 bytes of memory: default value of integer
 * fields is 0, so their wrapper's will take
 * 16 bytes; default String value is null, so
 * it'll take 0 byte.
 */
public class User {
    private int id;
    private String login;
    private String name;
    private String surname;
    private int age;

    public User() {
    }

    public User(int ID, String login, String name, String surname, int age) {
        this.id = ID;
        this.login = login;
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Prints how much memory take all fields
     */
    public void printFieldsMemory() {
        System.out.println(sizeOf(id));
        System.out.println(sizeOf(login));
        System.out.println(sizeOf(name));
        System.out.println(sizeOf(surname));
        System.out.println(sizeOf(age));
    }

    /**
     * Get size of memory this object takes
     *
     * @return Memory size
     */
    public long getSize() {
        return sizeOf(id)
                + sizeOf(login)
                + sizeOf(name)
                + sizeOf(surname)
                + sizeOf(age);
    }

    /**
     * When JVM is starting to collect the garbage
     * it prints message to us about it process
     *
     * @throws Throwable if smth went wrong
     */
    @Override
    protected void finalize() throws Throwable {
        System.out.println("removing");
    }
}
