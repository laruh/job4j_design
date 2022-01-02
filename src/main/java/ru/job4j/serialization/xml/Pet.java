package ru.job4j.serialization.xml;

import javax.xml.bind.annotation.*;
import java.util.Arrays;

@XmlRootElement(name = "pet")
@XmlAccessorType(XmlAccessType.FIELD)
public class Pet {
    @XmlAttribute
    private String name;

    @XmlAttribute
    private int age;

    @XmlAttribute
    private boolean vaccinated;
    private Owner owner;

    @XmlElementWrapper(name = "infos")
    @XmlElement(name = "info")
    private String[] info;

    public Pet() {
    }

    public Pet(String name, int age, boolean vaccinated, Owner owner,  String[] info) {
        this.name = name;
        this.age = age;
        this.vaccinated = vaccinated;
        this.owner = owner;
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
