package ru.job4j.serialization.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "owner")
public class Owner {
    @XmlAttribute
    private String contact;
    @XmlAttribute
    private String name;

    public Owner() {
    }

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
