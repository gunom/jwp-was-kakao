package utils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Request {
    private final String path;
    public Request(InputStream inputStream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        this.path = extractPath(reader);
    }

    private String extractPath(BufferedReader reader) throws IOException {
        String line = reader.readLine();
        return line.split(" ")[1];
    }

    public String getPath() {
        return path;
    }
}
