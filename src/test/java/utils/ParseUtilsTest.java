package utils;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class ParseUtilsTest {
    @Test
    void parseParamTest() {
        String httpRequest = "name=abc&age=20&job=programmer";
        Map<String, String> paramMap = ParseUtils.parseParam(httpRequest);
        assertThat(paramMap.get("name")).isEqualTo("abc");
        assertThat(paramMap.get("age")).isEqualTo("20");
        assertThat(paramMap.get("job")).isEqualTo("programmer");
    }
}