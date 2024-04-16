package controller;

import webserver.request.Method;
import webserver.request.Request;
import webserver.response.Response;

import java.util.List;

public class RequestMappingController {

    private static final List<Route> routes = List.of(
            new Route("/", new StaticResourceController(), Method.of("GET")),
            new Route("/user/create", new UserCreateController(), Method.of("POST")),
            new Route("/user/login", new LoginController(), Method.of("POST")),
            new Route("/user/login.html", new LoginController(), Method.of("GET")),
            new Route("/user/list.html", new UserListController(), Method.of("GET"))
    );

    public static Response handle(Request request) {
        return routes.stream()
                .filter(route -> route.matches(request.getPath().getPathWithoutParam(), request.getMethod()))
                .findFirst()
                .orElse(new Route(request.getPath().getPathWithoutParam(), new StaticResourceController(), Method.of("GET")))
                .handle(request);
    }
}
