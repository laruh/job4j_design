package ru.job4j.serialization.json;

public class Owner {
    private String contact;
    private String name;

    public Owner(String number, String name) {
        this.contact = number;
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
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
