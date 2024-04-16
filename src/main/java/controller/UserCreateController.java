package controller;

import model.User;
import service.UserService;
import webserver.request.Request;
import webserver.response.Response;

public class UserCreateController implements Controller {

    @Override
    public Response doGet(Request request) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Response doPost(Request request) {
        User user = UserService.addUser(request.getBody());
        String location = "/index.html";
        return Response.redirect(user.toString(), location);
    }
}
