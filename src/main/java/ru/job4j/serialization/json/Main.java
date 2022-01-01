package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {
    public static void main(String[] args) {
        final Person person = new Person(false, 30, new Contact("11-111"),
                new String[] {"Worker", "Married"});
        final Pet pet = new Pet("Bobik", 2,
                new Owner("555-35-35", "Petya"), true,
                new String[] {"male", "bulldog"});

        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(person));
        System.out.println(gson.toJson(pet));

        final String personJson =
                "{"
                        + "\"sex\":false,"
                        + "\"age\":35,"
                        + "\"contact\":"
                        + "{"
                        + "\"phone\":\"+7(924)111-111-11-11\""
                        + "},"
                        + "\"statuses\":"
                        + "[\"Student\",\"Free\"]"
                        + "}";
        final String petJson =
                "{"
                        + "\"name\":\"Bobik\","
                        + "\"age\":2,"
                        + "\"owner\":"
                        + "{"
                        + "\"contact\":\"8-800-555-35-35\","
                        + "\"name\":\"Petya\""
                        + "},"
                        + "\"vaccinated\":true,"
                        + "\"info\":"
                        + "[\"male\",\"bulldog\"]"
                        + "}";

        final Person personMod = gson.fromJson(personJson, Person.class);
        final Pet petMod = gson.fromJson(petJson, Pet.class);
        System.out.println(personMod);
        System.out.println(petMod);
    }
}
