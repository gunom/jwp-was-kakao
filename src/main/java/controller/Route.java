package controller;

import webserver.request.Method;
import webserver.request.Request;
import webserver.response.Response;

public class Route {
    private final String path;
    private final Controller controller;
    private final Method httpMethod;

    public Route(String path, Controller controller, Method httpMethod) {
        this.path = path;
        this.controller = controller;
        this.httpMethod = httpMethod;
    }

    public boolean matches(String requestPath, Method requestMethod) {
        return this.path.equalsIgnoreCase(requestPath) && this.httpMethod.equals(requestMethod);
    }

    public Response handle(Request request) {
        if (this.httpMethod.equals("GET")) {
            return controller.doGet(request);
        }
        if (this.httpMethod.equals("POST")) {
            return controller.doPost(request);
        }
        throw new UnsupportedOperationException("HTTP method not supported");
    }
}
