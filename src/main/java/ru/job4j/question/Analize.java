package ru.job4j.question;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        Info rsl = new Info(0, 0, 0);
        Map<Integer, String> curMap = current.stream()
                .collect(Collectors.toMap(User::getId, User::getName));
        int add = 0;
        int changed = 0;
        int deleted = 0;
        for (User user : previous) {
            String nameCurrent = curMap.get(user.getId());
            if (nameCurrent != null && !curMap.get(user.getId()).equals(user.getName())) {
                changed += 1;
                curMap.remove(user.getId());
            } else if (nameCurrent != null && curMap.get(user.getId()).equals(user.getName())) {
                curMap.remove(user.getId());
            } else if (nameCurrent == null) {
                deleted += 1;
            }
        }
        add += curMap.size();
        rsl.setAdded(add);
        rsl.setChanged(changed);
        rsl.setDeleted(deleted);
        return rsl;
    }
}
