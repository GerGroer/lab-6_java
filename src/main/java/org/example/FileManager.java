package org.example;

import java.io.IOException;
import java.nio.file.*;
import java.util.Comparator;
import java.util.stream.Stream;

public class FileManager {

    public static void manageFiles() throws IOException {

        String surname = "Shagalov";
        String name = "Max.txt";

        Path root = Paths.get(surname);

        // Создание основной директории (createDirectories сама проверяет существование)
        Files.createDirectories(root);

        // Создание файла (добавлена проверка exists)
        Path file = root.resolve(name);
        if (!Files.exists(file)) {
            Files.createFile(file);
        }

        // Создание вложенных директорий
        Path dir3 = root.resolve("dir1/dir2/dir3");
        Files.createDirectories(dir3);

        // Копирование файла
        Files.copy(
                file,
                dir3.resolve(name),
                StandardCopyOption.REPLACE_EXISTING
        );

        // Создание file1 (добавлена проверка exists)
        Path file1 = root.resolve("dir1/file1");
        if (!Files.exists(file1)) {
            Files.createFile(file1);
        }

        // Создание file2 (добавлена проверка exists)
        Path file2 = root.resolve("dir1/dir2/file2");
        if (!Files.exists(file2)) {
            Files.createFile(file2);
        }

        // Рекурсивный обход
        System.out.println("File structure:");

        try (Stream<Path> paths = Files.walk(root)) {
            paths.forEach(path -> {
                if (Files.isDirectory(path)) {
                    System.out.println("D: " + path);
                } else {
                    System.out.println("F: " + path);
                }
            });
        }

        // Удаление dir1 со всем содержимым
        Path dir1 = root.resolve("dir1");

        // Проверяем, существует ли dir1 перед удалением, чтобы избежать ошибок
        if (Files.exists(dir1)) {
            try (Stream<Path> paths = Files.walk(dir1)) {
                paths.sorted(Comparator.reverseOrder())
                        .forEach(path -> {
                            try {
                                Files.delete(path);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        });
            }
            System.out.println("Directory dir1 deleted.");
        }
    }
}
