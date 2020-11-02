package ru.job4j.serialization.json;

import org.json.JSONArray;
import org.json.JSONObject;

public class JSONApp {
    public static void main(String[] args) {
        Car car = new Car(true, 320, "Ferrari 458 SPECIALE",
                new Person("John Cruge"), "Automatic transmission", "Petrol");
        JSONObject jsonPerson = new JSONObject("{\"name\":" + car.getOwner().getName() + "}");
        JSONObject json = new JSONObject();
        json.put("isNew", car.isNew());
        json.put("maxSpeed", car.getMaxSpeed());
        json.put("model", car.getModel());
        json.put("owner", jsonPerson);
        json.put("options", new JSONArray(car.getOptionsAsList()));
        System.out.println(json.toString());
        System.out.println(new JSONObject(car));
    }
}
