package controller;

import webserver.request.Path;
import webserver.request.Request;
import webserver.response.Response;

import java.util.Map;
import java.util.function.Function;

public class RequestMappingController {

    private static final Map<String, Function<Request, Response>> controllerMap = Map.of(
        "/", RequestMappingController::handleHome,
        "/user/create", UserCreateController::doPost,
        "/user/login", LoginController::doPost,
        "/user/login.html", LoginController::doGet,
        "/user/list.html", UserListController::doGet
    );

    public static Response handle(Request request) {
        Path path = request.getPath();
        return controllerMap
            .getOrDefault(path.getPathWithoutParam(), StaticResourceController::doGet)
            .apply(request);
    }

    private static Response handleHome(Request request) {
        return Response.redirect("home", "/index.html");
    }
}
