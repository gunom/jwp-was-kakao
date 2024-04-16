package webserver.request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

public class Request {

    private final RequestHeader requestHeader;
    private final RequestBody requestBody;

    public Request(InputStream inputStream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        requestHeader = new RequestHeader(reader);
        requestBody = new RequestBody(reader, requestHeader.getContentLength());
    }

    public Path getPath() {
        return requestHeader.getRequestLine().getPath();
    }

    public Map<String, String> getBody() {
        return requestBody.getBody();
    }

    public Map<String, String> getCookies() {
        return requestHeader.getCookies();
    }

    public Method getMethod() {
        return requestHeader.getRequestLine().getMethod();
    }
}