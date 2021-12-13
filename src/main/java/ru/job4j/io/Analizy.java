package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Stack;

public class Analizy {
    public static void unavailable(String source, String target) {
        try (
                BufferedReader in = new BufferedReader(new FileReader(source));
                PrintWriter out = new PrintWriter(new FileOutputStream(target))
        ) {
            Stack<String> temp = new Stack<>();
            in.lines()
                    .map(line -> line.split(" "))
                    .forEach(line -> {
                        if ((line[0].equals("400") || line[0].equals("500")) && temp.isEmpty()) {
                            temp.push(line[1]);
                        } else if (line[0].equals("200") && temp.size() == 1) {
                            String rsl = temp.pop() + ";" + line[1];
                            out.println(rsl);
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        unavailable("./data/server.log", "./data/unavailable.csv");
        unavailable("./data/server1.log", "./data/unavailable1.csv");
    }
}
