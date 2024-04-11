package webserver;

import org.junit.jupiter.api.Test;
import webserver.request.RequestBody;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import static org.assertj.core.api.Assertions.assertThat;

public class RequestBodyTest {

    @Test
    public void testRequestBodyParsing() throws IOException {
        String httpRequest = "name=abc&age=20&job=programmer";

        BufferedReader reader = new BufferedReader(new StringReader(httpRequest));

        RequestBody requestBody = new RequestBody(reader, httpRequest.length());

        assertThat(requestBody.getBody().get("name")).isEqualTo("abc");
        assertThat(requestBody.getBody().get("age")).isEqualTo("20");
        assertThat(requestBody.getBody().get("job")).isEqualTo("programmer");
    }
}