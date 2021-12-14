package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private final HashMap<FileProperty, Path> map = new HashMap<>();
    private final List<Path> duplicates = new ArrayList<>();

    public List<Path> getDuplicates() {
        return duplicates;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProperty = new FileProperty(
                file.toFile().length(),
                file.toFile().getName()
        );
        if (!map.containsKey(fileProperty)) {
            duplicates.add(file.toAbsolutePath());
        } else {
            map.put(fileProperty, file.toAbsolutePath());
        }
        return super.visitFile(file, attrs);
    }
}
