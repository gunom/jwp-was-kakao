package controller;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.function.Function;

import model.User;
import service.UserService;
import utils.FileIoUtils;
import webserver.request.Path;
import webserver.request.Request;
import webserver.response.ApiResponse;
import webserver.response.FileResponse;
import webserver.response.Response;

public class Controller {

    private static final Map<String, Function<Request, Response>> controllerMap = Map.of(
        "/user/create", Controller::handleUserCreate
    );

    public static Response handle(Request request) {
        Path path = request.getPath();
        return controllerMap
            .getOrDefault(path.getPathWithoutParam(), Controller::handleDefault)
            .apply(request);
    }

    private static Response handleUserCreate(Request request) {
        User user = UserService.addUser(request.getPath().getParams());
        return ApiResponse.of(user.toString());
    }

    private static Response handleDefault(Request request) {
        // String contentType = request.getContentType();
        // byte[] body = getFormFileResource(request.getPath());
        return FileResponse.of(request.getPath().getPathWithoutParam());
    }

    private static byte[] getFormFileResource(Path path) throws IOException, URISyntaxException {
        String filepath = FileIoUtils.convertPathToFilePath(path.getPathWithoutParam());
        return FileIoUtils.loadFileFromClasspath(filepath);
    }
}
