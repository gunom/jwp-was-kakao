package utils;

import static org.assertj.core.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.junit.jupiter.api.Test;

import webserver.request.Request;

public class RequestTest {
    @Test
    void Request_헤더에서_Path를_추출할_수_있다() throws IOException {
        // given
        String request_stream = "GET /index.html HTTP/1.1\n"
            + "Host: localhost:8080\n"
            + "Connection: keep-alive\n"
            + "Accept: */*\n";

        // when
        Request request = new Request(new ByteArrayInputStream(request_stream.getBytes()));

        // then
        assertThat(request.getPath()).isEqualTo("/index.html");
    }
}
