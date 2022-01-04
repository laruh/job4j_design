package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

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

        /* JSONObject из json-строки строки */
        JSONObject jsonOwner = new JSONObject(
                "{\"contact\":\"8-800-555-35-35\",\"name\":\"Petya\"}"
        );

        /* JSONArray из ArrayList */
        List<String> list = new ArrayList<>();
        list.add("male");
        list.add("bulldog");
        JSONArray jsonInfo = new JSONArray(list);

        /* JSONObject напрямую методом put */
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", pet.getName());
        jsonObject.put("age", pet.getAge());
        jsonObject.put("owner", jsonOwner);
        jsonObject.put("vaccinated", pet.isVaccinated());
        jsonObject.put("info", jsonInfo);

        /* Выведем результат в консоль */
        System.out.println(jsonObject);

        /* Преобразуем объект person в json-строку */
        System.out.println(new JSONObject(pet));
    }
}
