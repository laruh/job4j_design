package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;

/**
 * Задача класса прочитать данные из CSV файла и вывести их.
 * @author Alina Sharonina
 * @version 2.0
 */
public class CSVReader {
    /**
     * Метод предназначен для формирования шапки таблицы в новом файле,
     * а также для создания списка индексов отфильтрованных столбцов.
     * @param scanner сканер.
     * @param delimiter разделитель.
     * @param filter содержит названия столбцов, по которым будет поиск.
     * @param out содержит путь к файлу записи.
     * @return indexes список индексов, по которым мы будем искать таблицы в методе {@code handle}
     */
    public static List<Integer> makeHead(Scanner scanner, String delimiter, String filter,
                                         PrintWriter out) {
        List<Integer> indexes = new ArrayList<>();
        String[] lineHead = scanner.nextLine().split(delimiter);
        StringBuilder builderHead = new StringBuilder();
        for (int i = 0; i < lineHead.length; i++) {
            if (filter.contains(lineHead[i])) {
                indexes.add(i);
                builderHead.append(lineHead[i]).append(";");
            }
        }
        builderHead.setLength(Math.max(builderHead.length() - 1, 0));
        out.println(builderHead);
        return indexes;
    }

    /**
     * Метод предназначен для заполнения остальной части таблицы в заполняемом файле.
     * В качестве входных данных метода задается путь к файлу path,
     * разделитель delimiter, приемник данных out и фильтр по столбцам filter.
     * @param argsName содержит массив параметров: path, delimiter, out, filter.
     */
    public static void handle(ArgsName argsName) throws IOException {
        String inPath = argsName.get("path");
        String delimiter = argsName.get("delimiter");
        String outPath = argsName.get("out");
        String filter = argsName.get("filter");
        validation(inPath);
        File fileIn = Paths.get(inPath).toFile();
        File tempFile = File.createTempFile("data", null);
        try (
                PrintWriter out = new PrintWriter(new FileOutputStream(tempFile));
                Scanner scanner = new Scanner(fileIn)
        ) {
            List<Integer> indexes = makeHead(scanner, delimiter, filter, out);
            while (scanner.hasNextLine()) {
                StringBuilder builder = new StringBuilder();
                String[] nextLine = scanner.nextLine().split(delimiter);
                for (Integer index : indexes) {
                    builder.append(nextLine[index]).append(";");
                }
                builder.setLength(Math.max(builder.length() - 1, 0));
                out.println(builder);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if ("stdout".equals(outPath)) {
            try (BufferedReader in = new BufferedReader(new FileReader(tempFile))) {
                in.lines().forEach(System.out::println);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Path out = Paths.get(outPath);
            Files.copy(tempFile.toPath(), out, StandardCopyOption.REPLACE_EXISTING);
        }
    }

    /**
     * Метод предназначен для валидации файла чтения.
     * @param inPath путь до считываемого файла.
     */
    public static void validation(String inPath) {
        File fileIn = Paths.get(inPath).toFile();
        if (!fileIn.exists()) {
            throw new IllegalArgumentException(
                    String.format("Not exist %s", fileIn.getAbsoluteFile()));
        }
    }

    public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            throw new IllegalArgumentException(
                    "Root folder is null. Usage java -jar dir.jar ROOT_FOLDER.");
        }
        ArgsName argsName = ArgsName.of(args);
        handle(argsName);
    }
}
