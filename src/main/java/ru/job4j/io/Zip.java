package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public static void packFiles(List<File> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(
                new BufferedOutputStream(new FileOutputStream(target)))
        ) {
            for (File source : sources) {
                zip.putNextEntry(new ZipEntry(source.getPath()));
                try (BufferedInputStream out = new BufferedInputStream(
                        new FileInputStream(source))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Path> excludeFiles(Path root, String exclude) throws IOException {
        Predicate<Path> condition = p -> !p.toFile().getName().endsWith("." + exclude);
        return Search.search(root, condition);
    }

    public static void validation(File start, File out) {
        if (!start.exists() && !out.exists()) {
            throw new IllegalArgumentException(
                    String.format("Not exist %s", start.getAbsoluteFile()
                            + System.lineSeparator() + out.getAbsolutePath()));
        }
        if (!start.isDirectory() && !out.isDirectory()) {
            throw new IllegalArgumentException(
                    String.format("Not directory %s", start.getAbsoluteFile()
                            + System.lineSeparator() + out.getAbsolutePath()));
        }
    }

    public static void main(String[] args) throws IOException {
        String directory = "d";
        String exclude = "e";
        String output = "o";
        if (args.length == 0) {
            throw new IllegalArgumentException(
                    "Root folder is null. Usage java -jar dir.jar ROOT_FOLDER.");
        }
        ArgsName argsName = ArgsName.of(args);
        Path start = Paths.get(argsName.get(directory));
        File file = start.toFile();
        File out = Paths.get(argsName.get(output)).toFile();
        validation(file, out);
        List<Path> listPaths = excludeFiles(start, argsName.get(exclude));
        List<File> sources = listPaths.stream()
                .map(Path::toFile)
                .collect(Collectors.toList());
        packFiles(sources, out);
    }
}
