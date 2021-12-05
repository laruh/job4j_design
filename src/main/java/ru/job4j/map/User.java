package ru.job4j.map;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    public static void main(String[] args) {
        Calendar birthday = Calendar.getInstance();
        User user1 = new User("Ivan", 0, birthday);
        User user2 = new User("Ivan", 0, birthday);

        Map<User, Object> map = new HashMap<User, Object>();
        map.put(user1, new Object());
        map.put(user2, new Object());

        for (Map.Entry<User, Object> item : map.entrySet()) {
            System.out.printf("Key: %s  Value: %s \n", item.getKey(), item.getValue());
        }

        Iterator<Map.Entry<User, Object>> iter = map.entrySet().iterator();
        User value1 = iter.next().getKey();
        User value2 = iter.next().getKey();
        System.out.println(value1.equals(value2));
        System.out.println("hashCode1 = " + value1.hashCode()
                + " hashCode2 = " + value2.hashCode());
    }
}
