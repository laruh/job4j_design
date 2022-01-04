package ru.job4j.serialization.json;

import java.util.Arrays;

public class Pet {
    private String name;
    private int age;
    private Owner owner;
    private boolean vaccinated;
    private String[] info;

    public Pet(String name, int age, Owner owner, boolean vaccinated, String[] info) {
        this.name = name;
        this.age = age;
        this.owner = owner;
        this.vaccinated = vaccinated;
        this.info = info;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public boolean isVaccinated() {
        return vaccinated;
    }

    public void setVaccinated(boolean vaccinated) {
        this.vaccinated = vaccinated;
    }

    public String[] getInfo() {
        return info;
    }

    public void setInfo(String[] info) {
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
