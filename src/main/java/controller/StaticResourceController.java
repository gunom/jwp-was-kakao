package controller;

import utils.ContentTypeResolver;
import utils.FileIoUtils;
import utils.FilePathConverter;
import webserver.request.Path;
import webserver.request.Request;
import webserver.response.Response;

import java.io.IOException;
import java.net.URISyntaxException;

public class StaticResourceController implements Controller{

    @Override
    public Response doGet(Request request) {
        Path path = request.getPath();
        String url = FilePathConverter.convertPathToFilePath(path.getPathWithoutParam());
        String contentType = ContentTypeResolver.getContentType(url);
        try {
            return Response.of(FileIoUtils.loadFileFromClasspath(url), contentType);
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Response doPost(Request request) {
        throw new UnsupportedOperationException();
    }
}
