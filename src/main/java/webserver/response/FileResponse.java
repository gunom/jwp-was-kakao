package webserver.response;

import java.io.IOException;
import java.net.URISyntaxException;

import utils.FileIoUtils;

public class FileResponse implements Response {

    private final byte[] body;
    private final byte[] header;

    private FileResponse(String path) throws IOException, URISyntaxException {
        String uri = FileIoUtils.convertPathToFilePath(path);
        this.body = FileIoUtils.loadFileFromClasspath(uri);
        this.header = ("HTTP/1.1 200 OK \r\n"
            + "Content-Type: " + getContentType(path) + ";charset=utf-8\r\n"
            + "Content-Length: " + body.length + "\r\n"
            + "\r\n").getBytes();
    }

    private String getContentType(String path) {
        if (path.endsWith(".html")) {
            return "text/html";
        }
        if (path.endsWith("css")) {
            return "text/css";
        }
        if (path.endsWith("js")) {
            return "text/javascript";
        }
        if (path.endsWith("png")) {
            return "image/png";
        }
        if (path.endsWith("woff")) {
            return "font/woff";
        }
        if (path.endsWith("woff2")) {
            return "font/woff2";
        }
        if (path.endsWith("ttf")) {
            return "font/ttf";
        }
        return "text/plain";
    }

    public static Response of(String path) {
        try {
            return new FileResponse(path);
        } catch (Exception e) {
            return new NotFoundResponse();
        }
    }
    @Override
    public byte[] getHeader() {
        return header;
    }

    @Override
    public byte[] getBody() {
        return body;
    }

}
