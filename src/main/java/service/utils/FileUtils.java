package service.utils;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileUtils {

    private static final Logger logger = Logger.getLogger(FileUtils.class.getName());

    public static Optional<String> getExtensionByStringHandling(File requestFile) {
        String filename = requestFile.toString();
        return Optional.ofNullable(filename)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(filename.lastIndexOf(".") + 1));
    }

    public static void createDirectory(String path) throws IOException {
        File directory = new File(path);
        if (!directory.exists()) {
            Files.createDirectory(Paths.get(path));
        }
    }

    public static void deleteFile(File file) {
        if (file.delete()) {
            logger.info("Deleted the file: " + file.getName());
        } else {
            throw new UnsupportedOperationException("Failed to delete the file.");
        }

    }

    public static void moveTo(File source, File dest) throws IOException {
        copyTo(source, dest);
        deleteFile(source);
        logger.info("Move the file: " + source.getName() + " to location: " + dest.getAbsolutePath());
    }

    public static void copyTo(File source, File dest) throws IOException {
        var source1 = new RandomAccessFile(source.getAbsolutePath(), "r");
        var dest1 = new RandomAccessFile(dest.getAbsolutePath(), "rw");

        try (var sfc = source1.getChannel();
             var dfc = dest1.getChannel()) {

            dfc.transferFrom(sfc, 0, sfc.size());
        }
        logger.info("Copy the file: " + source.getName() + " to location: " + dest.getAbsolutePath());
    }

    public static Set<File> getFilesFromDirectory(String dir) {

        if (!new File(dir).isDirectory()) {
            throw new UnsupportedOperationException("Not found directory in chosen location");
        }

        if (Objects.requireNonNull(new File(dir).listFiles()).length == 0) {
            throw new UnsupportedOperationException("Not found any file in chosen location");
        }

        return Stream.of(Objects.requireNonNull(new File(dir).listFiles()))
                .filter(file -> !file.isDirectory())
                .collect(Collectors.toSet());
    }
}
