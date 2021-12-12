package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LogFilter {
    public static List<String> filter(String file) {
        List<String> rsl = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            in.lines()
                    .filter(line -> {
                        String[] elem = line.split(" ");
                        return elem[elem.length - 2].equals("404");
                    })
                    .forEach(rsl::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rsl;
    }

    public static void save(List<String> log, String file) {
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(file)
                ))){
            for (String elem : log) {
                out.write(elem + System.lineSeparator());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        List<String> log = filter("log.txt");
        String[] lines = log.toString().split(",");
        for (String line : lines) {
            System.out.println(line);
        }
        save(log, "404.txt");
    }
}
