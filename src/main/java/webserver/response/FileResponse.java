package webserver.response;

import java.io.IOException;
import java.net.URISyntaxException;

import utils.ContentTypeResolver;
import utils.FileIoUtils;
import utils.FilePathConverter;

public class FileResponse implements Response {

    private final byte[] body;
    private final byte[] header;

    private FileResponse(String path) throws IOException, URISyntaxException {
        String uri = FilePathConverter.convertPathToFilePath(path);
        this.body = FileIoUtils.loadFileFromClasspath(uri);
        this.header = ("HTTP/1.1 200 OK \r\n"
            + "Content-Type: " + getContentType(path) + ";charset=utf-8\r\n"
            + "Content-Length: " + body.length + "\r\n"
            + "\r\n").getBytes();
    }

    private String getContentType(String path) {
        return ContentTypeResolver.getContentType(path);
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
