package utils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.assertj.core.api.Assertions.assertThat;

public class FileIoUtilsTest {
    private static final Logger log = LoggerFactory.getLogger(FileIoUtilsTest.class);

    @Test
    void loadFileFromClasspath() throws Exception {
        byte[] body = FileIoUtils.loadFileFromClasspath("./templates/index.html");
        log.debug("file : {}", new String(body));
    }

    @ParameterizedTest
    @CsvSource(value = {"/index.html,./templates/index.html", "/css/styles.css,./static/css/styles.css"})
    void testConvertPathToFilePath(String path, String expected) {
        String filePath = FilePathConverter.convertPathToFilePath(path);
        assertThat(filePath).isEqualTo(expected);
    }
}