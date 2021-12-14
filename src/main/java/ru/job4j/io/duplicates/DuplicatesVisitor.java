package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private final HashSet<FileProperty> files = new HashSet<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProperty = new FileProperty(
                file.toFile().length(),
                file.toFile().getName()
        );
        if (files.contains(fileProperty)) {
            System.out.println("Path to the duplicate= " + file.toAbsolutePath()
                    + System.lineSeparator()
                    + "Name= " + fileProperty.getName());
        } else {
            files.add(fileProperty);
        }
        return super.visitFile(file, attrs);
    }
}
