package webserver.request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Request {

    private final RequestHeader requestHeader;


    public Request(InputStream inputStream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        requestHeader = new RequestHeader(reader);
    }

    public Path getPath() {
        return requestHeader.getRequestLine().getPath();
    }

    public String getContentType() {
        return requestHeader.getContentType();
    }
}