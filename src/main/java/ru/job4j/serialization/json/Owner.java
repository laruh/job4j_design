package ru.job4j.serialization.json;

public class Owner {
    private final String contact;
    private final String name;

    public Owner(String number, String name) {
        this.contact = number;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Owner{"
                + "number='" + contact
                + '\''
                + ", name='" + name
                + '\''
                + '}';
    }
}
