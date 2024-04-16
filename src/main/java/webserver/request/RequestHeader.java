package webserver.request;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class RequestHeader {

    private final RequestLine requestLine;
    private final Map<String, String> headers = new HashMap<>();
    private final Map<String, String> cookies = new HashMap<>();

    public RequestHeader(BufferedReader requests) throws IOException {
        String requestLine = requests.readLine();
        this.requestLine = new RequestLine(requestLine);
        createHeaders(requests);
    }

    private void createHeaders(BufferedReader requests) throws IOException {
        String line;
        while ((line = requests.readLine()) != null && !line.isEmpty()) {
            String[] keyValue = line.split(": ");
            createCookie(keyValue);
            headers.put(keyValue[0], keyValue[1]);
        }
    }

    private void createCookie(String[] keyValue) {
        if (keyValue[0].equals("Cookie")) {
            String[] cookie = keyValue[1].split("; ");
            Arrays.stream(cookie).forEach(
                c -> {
                    String[] cookieKeyValue = c.split("=");
                    cookies.put(cookieKeyValue[0], cookieKeyValue[1]);
                }
            );
        }
    }

    public RequestLine getRequestLine() {
        return requestLine;
    }

    public int getContentLength() {
        return Integer.parseInt(headers.getOrDefault("Content-Length", "0"));
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public Map<String, String> getCookies() {
        return cookies;
    }
}
