package controller;

import model.User;
import service.UserService;
import webserver.request.Request;
import webserver.response.Response;

public class UserCreateController {

    public static Response doPost(Request request) {
        User user = UserService.addUser(request.getBody());
        String location = "/index.html";
        return Response.redirect(user.toString(), location);
    }
}
