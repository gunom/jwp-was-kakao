package webserver;

import org.junit.jupiter.api.Test;
import webserver.request.RequestHeader;
import webserver.request.RequestLine;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class RequestHeaderTest {

    @Test
    public void testRequestHeaderParsing() throws Exception {
        String httpRequest = "GET /index.html HTTP/1.1\n" +
                "Host: www.example.com\n" +
                "Content-Type: text/plain\n" +
                "Content-Length: 100\n" +
                "\n";

        BufferedReader reader = new BufferedReader(new StringReader(httpRequest));

        RequestHeader requestHeader = new RequestHeader(reader);

        Map<String, String> headers = requestHeader.getHeaders();
        assertThat(headers.get("Host")).isEqualTo("www.example.com");
        assertThat(headers.get("Content-Type")).isEqualTo("text/plain");
        assertThat(headers.get("Content-Length")).isEqualTo("100");
    }
}
