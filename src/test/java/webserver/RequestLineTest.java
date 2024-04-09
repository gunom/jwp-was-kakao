package webserver;

import org.junit.jupiter.api.Test;
import webserver.request.RequestLine;

import static org.assertj.core.api.Assertions.assertThat;

public class RequestLineTest {

    @Test
    public void testRequestLineParsing() {
        String requestLineString = "GET /index.html HTTP/1.1";

        RequestLine requestLine = new RequestLine(requestLineString);

        assertThat(requestLine.getMethod().getMethod()).isEqualTo("GET");
        assertThat(requestLine.getPath().getPathWithoutParam()).isEqualTo("/index.html");
        assertThat(requestLine.getProtocol().getProtocol()).isEqualTo("HTTP/1.1");
    }
}