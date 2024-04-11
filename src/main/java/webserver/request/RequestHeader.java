package webserver.request;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RequestHeader {

    private final RequestLine requestLine;
    private final Map<String, String> headers = new HashMap<>();

    public RequestHeader(BufferedReader requests) throws IOException {
        String requestLine = requests.readLine();
        this.requestLine = new RequestLine(requestLine);
        createHeaders(requests);
    }

    private void createHeaders(BufferedReader requests) throws IOException {
        String line;
        while ((line = requests.readLine()) != null && !line.isEmpty()) {
            String[] keyValue = line.split(": ");
            headers.put(keyValue[0], keyValue[1]);
        }
    }

    public RequestLine getRequestLine() {
        return requestLine;
    }

    public String getHeader(String s) {
        return headers.get(s);
    }

    public int getContentLength() {
        return Integer.parseInt(headers.getOrDefault("Content-Length", "0"));
    }

    public Map<String, String> getHeaders() {
        return headers;
    }
}
