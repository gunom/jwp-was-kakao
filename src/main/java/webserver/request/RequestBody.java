package webserver.request;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import utils.IOUtils;

public class RequestBody {

    private final Map<String, String> body = new HashMap<>();

    public RequestBody(BufferedReader reader, int contentLength) throws IOException {
        if (contentLength == 0) {
            return;
        }
        String bodyString = IOUtils.readData(reader, contentLength);
        Arrays.stream(bodyString.split("&")).forEach(
            keyValue -> {
                String[] split = keyValue.split("=");
                body.put(split[0], split[1]);
            }
        );
    }

    public Map<String, String> getBody() {
        return body;
    }
}
