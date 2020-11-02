package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;
import java.util.List;

public class Car {
    private boolean isNew;
    private int maxSpeed;
    private String model;
    private Person owner;
    private String[] options;

    public Car(boolean isNew, int maxSpeed, String model, Person owner, String... options) {
        this.isNew = isNew;
        this.maxSpeed = maxSpeed;
        this.model = model;
        this.owner = owner;
        this.options = options;
    }

    @Override
    public String toString() {
        return "Car{"
                + "isNew=" + isNew
                + ", maxSpeed=" + maxSpeed
                + ", model='" + model + '\''
                + ", owner=" + owner
                + ", options=" + Arrays.toString(options)
                + '}';
    }

    public boolean isNew() {
        return isNew;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public String getModel() {
        return model;
    }

    public Person getOwner() {
        return owner;
    }

    public List<String> getOptionsAsList() {
        return Arrays.asList(options);
    }

    public static void main(String[] args) {
        Car car1 = new Car(false, 200, "Hyundai Solaris",
                new Person("Ben Gun"), "Mechanical transmission", "Petrol");
        Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(car1));
        String car2JSONData =
                "{"
                        + "\"isNew\":true,"
                        + "\"maxSpeed\":240,"
                        + "\"model\":\"Lexus RX300\","
                        + "\"owner\":"
                            + "{"
                                + "\"name\":\"Jack Jonson\""
                            + "},"
                        + "\"options\":"
                            + "[\"Automatic transmission\",\"Diesel\"]"
                + "}";
        Car car2 = gson.fromJson(car2JSONData, Car.class);
        System.out.println(car2);
    }
}
