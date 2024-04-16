package controller;

import java.util.Map;
import java.util.function.Function;

import model.User;
import service.UserService;
import webserver.request.Path;
import webserver.request.Request;
import webserver.response.ApiResponse;
import webserver.response.FileResponse;
import webserver.response.Response;

public class Controller {

    private static final Map<String, Function<Request, Response>> controllerMap = Map.of(
        "/", Controller::handleHome,
        "/user/create", Controller::handleUserCreate
    );

    public static Response handle(Request request) {
        Path path = request.getPath();
        return controllerMap
            .getOrDefault(path.getPathWithoutParam(), Controller::handleDefault)
            .apply(request);
    }

    private static Response handleHome(Request request) {
        return ApiResponse.of("Hello World");
    }

    private static Response handleUserCreate(Request request) {
        User user = UserService.addUser(request.getBody());
        String location = "/index.html";
        return ApiResponse.of(user.toString(), location);
    }

    private static Response handleDefault(Request request) {
        return FileResponse.of(request.getPath().getPathWithoutParam());
    }
}