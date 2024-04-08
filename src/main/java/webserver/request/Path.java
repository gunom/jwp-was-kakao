package webserver.request;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Path {

    private final String path;
    private final Map<String, String> params = new HashMap<>();

    public Path(String path) {
        this.path = path;
        if (path.contains("?")) {
            parseParams();
        }
    }

    private void parseParams() {
        String[] tokens = path.split("\\?");
        String[] params = tokens[1].split("&");
        Arrays.stream(params)
                .forEach(param -> {
                    String[] keyValue = param.split("=");
                    this.params.put(keyValue[0], keyValue[1]);
                });
    }

    public String getPathWithoutParam() {
        return path.split("\\?")[0];
    }

    public Map<String, String> getParams() {
        return params;
    }
}
