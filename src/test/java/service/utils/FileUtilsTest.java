package service.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;

public class FileUtilsTest {

    private static final String DIRECTORY_PATH = "src/test/java/service/impl/data";
    private static final String JSON = "src/test/java/service/impl/data/test.json";
    private static final File REQUEST_FILE = new File(JSON);

    @Test
    void testGetExtensionByStringHandling() {
        Assertions.assertEquals(FileUtils.getExtensionByStringHandling(REQUEST_FILE).orElse(null), "json");
    }

    @Test
    public void testGetFilesFromDirectory() {
        Assertions.assertTrue(FileUtils.getFilesFromDirectory(DIRECTORY_PATH).stream().anyMatch(s -> s.getName().contains("test")));
    }

    @Test
    public void testGetFilesFromDirectoryThrowException() {
        Assertions.assertThrows(UnsupportedOperationException.class,
                () -> FileUtils.getFilesFromDirectory("empty"), "Not found directory in chosen location");
    }
}