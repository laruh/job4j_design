package ru.job4j.serialization.json;

import java.util.Arrays;

public class Pet {
    private final String name;
    private final int age;
    private final Owner owner;
    private final boolean vaccinated;
    private final String[] info;

    public Pet(String name, int age, Owner owner, boolean vaccinated, String[] info) {
        this.name = name;
        this.age = age;
        this.owner = owner;
        this.vaccinated = vaccinated;
        this.info = info;
    }

    @Override
    public String toString() {
        return "Pet{"
                + "name='" + name + '\''
                + ", age=" + age
                + ", owner=" + owner
                + ", vaccinated=" + vaccinated
                + ", info=" + Arrays.toString(info)
                + '}';
    }
}
