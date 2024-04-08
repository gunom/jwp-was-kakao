package webserver.request;

import java.util.HashMap;
import java.util.Map;

public class RequestBody {

    private final Map<String, String> body = new HashMap<>();

    public RequestBody(String body) {
        String[] keyValue = body.split("&");
        for (String pair : keyValue) {
            String[] split = pair.split("=");
            this.body.put(split[0], split[1]);
        }
    }
}
