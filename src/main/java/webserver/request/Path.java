package webserver.request;

import java.util.Map;

import utils.ParseUtils;

public class Path {

    private final String path;
    private Map<String, String> params;

    public Path(String path) {
        this.path = path;
        if (path.contains("?")) {
            parseParams();
        }
    }

    private void parseParams() {
        String[] tokens = path.split("\\?");
        params = ParseUtils.parseParam(tokens[1]);
    }

    public String getPathWithoutParam() {
        return path.split("\\?")[0];
    }

    public Map<String, String> getParams() {
        return params;
    }
}