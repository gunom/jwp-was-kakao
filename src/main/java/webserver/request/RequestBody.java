package webserver.request;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;

import utils.IOUtils;
import utils.ParseUtils;

public class RequestBody {

    private Map<String, String> body;

    public RequestBody(BufferedReader reader, int contentLength) throws IOException {
        if (contentLength == 0) {
            return;
        }
        String bodyString = IOUtils.readData(reader, contentLength);
        this.body = ParseUtils.parseParam(bodyString);
    }

    public Map<String, String> getBody() {
        return body;
    }
}