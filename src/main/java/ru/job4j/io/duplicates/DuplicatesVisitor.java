package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;
import java.util.stream.Collectors;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private final HashMap<FileProperty, List<Path>> map = new HashMap<>();

    public HashMap<FileProperty, List<Path>> getMap() {
        return map;
    }

    public List<List<Path>> getDuplicates() {
        return getMap().values().stream()
                .filter(elem -> elem.size() > 1)
                .collect(Collectors.toList());
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProperty = new FileProperty(
                file.toFile().length(),
                file.toFile().getName()
        );
        if (map.containsKey(fileProperty)) {
            map.get(fileProperty).add(file.toAbsolutePath());
        } else {
            List<Path> el = new ArrayList<>();
            el.add(file.toAbsolutePath());
            map.put(fileProperty, el);
        }
        return super.visitFile(file, attrs);
    }
}
