package ru.job4j.io;

import java.io.FileOutputStream;
import java.io.BufferedOutputStream;
import java.io.PrintWriter;

public class ResultFile {
    public static String matrix(int size) {
        StringBuilder array = new StringBuilder();
        for (int row = 0; row < size; row++) {
            for (int cell = 0; cell < size; cell++) {
                array.append((row + 1) * (cell + 1));
                array.append("  ");
            }
            array.append(System.lineSeparator());
        }
        return array.toString();
    }

    public static void main(String[] args) {
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream("D:/Users/Загрузки/resultMatrix.txt")
                ))) {
            out.println(matrix(10));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
