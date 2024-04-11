package webserver;

import org.junit.jupiter.api.Test;
import webserver.request.Path;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class PathTest {
    @Test
    public void PathParsingTest() {
        String pathString = "/index.html?name=abc&age=20&job=programmer";
        Path path = new Path(pathString);
        assertThat(path.getPathWithoutParam()).isEqualTo("/index.html");

        Map<String, String> params = path.getParams();
        assertAll(
                () -> assertThat(params.get("name")).isEqualTo("abc"),
                () -> assertThat(params.get("age")).isEqualTo("20"),
                () -> assertThat(params.get("job")).isEqualTo("programmer")
        );
    }
}