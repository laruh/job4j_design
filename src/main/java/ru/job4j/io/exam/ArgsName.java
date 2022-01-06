package ru.job4j.io.exam;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ArgsName {
    private Map<String, String> values = new HashMap<>();

    public String get(String key) {
        String rsl = values.get(key);
        if (rsl == null) {
            throw new IllegalArgumentException("Parameter is missing.");
        }
        return rsl;
    }

    private void parse(String[] args) {
        Arrays.stream(args)
                .map(elem -> elem.split("="))
                .forEach(elem -> {
                    if (elem.length != 2 || !elem[0].startsWith("-")) {
                        throw new IllegalArgumentException(
                                "Wrong pattern. Provide two parameters or start key with -");
                    }
                    values.put(elem[0].replaceFirst("-", ""), elem[1]);
                });
    }

    public static ArgsName of(String[] args) {
       ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }
}