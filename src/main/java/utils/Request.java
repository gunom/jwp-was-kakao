package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Request {
    private final String path;
    private final Map<String, String> requestMap;
    private final String contentType;


    public Request(InputStream inputStream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        this.requestMap = parse(reader);
        this.path = extractPath(reader);
        this.contentType = requestMap.get("Accept");
    }

    private String extractPath(BufferedReader reader) throws IOException {
        String line = reader.readLine();
        return line.split(" ")[1];
    }

    private static Map<String, String> parse(BufferedReader request) {
        String requestString = request.lines().collect(Collectors.toList()).toString();
        Map<String, String> parsedRequest = new HashMap<>();

        // Remove brackets from the request
        String cleanedRequest = requestString.substring(9, requestString.length() - 3);

        // Split the request into individual header fields
        String[] headerFields = cleanedRequest.split(", ");

        // Iterate over each header field and parse key-value pairs
        for (String field : headerFields) {
            String[] keyValue = field.split(": ");
            String key = keyValue[0];
            String value = keyValue[1];
            parsedRequest.put(key, value);
        }

        return parsedRequest;
    }

    public String getPath() {
        return path;
    }

    public String getContentType() {
        return contentType;
    }
}
