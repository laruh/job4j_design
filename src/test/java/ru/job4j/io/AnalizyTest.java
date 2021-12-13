package ru.job4j.io;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AnalizyTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void unavailable() throws IOException {
        File source = folder.newFile("server.log");
        File target = folder.newFile("unavailable.csv");
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("200 10:56:01");
            out.println("200 11:48:01");
            out.println("500 11:49:02");
            out.println("400 12:58:01");
            out.println("200 12:59:01");
        }
        Analizy.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        List<String> rsl = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(rsl::add);
        }
        List<String> expect = List.of("11:49:02;12:59:01");
        assertThat(rsl, is(expect));
    }

    @Test
    public void unavailableAnother() throws IOException {
        File source = folder.newFile("server.log");
        File target = folder.newFile("unavailable.csv");
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("500 10:56:01");
            out.println("200 11:48:01");
            out.println("500 11:49:02");
            out.println("400 12:58:01");
            out.println("200 13:59:01");
        }
        List<String> rsl = new ArrayList<>();
        Analizy.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(rsl::add);
        }
        List<String> expect = List.of(
                "10:56:01;11:48:01",
                "11:49:02;13:59:01"
        );
        assertThat(rsl, is(expect));
    }
}