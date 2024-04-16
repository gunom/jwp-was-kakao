package controller;

import model.User;
import service.UserService;
import webserver.request.Request;
import webserver.response.Response;
import webserver.session.Session;
import webserver.session.SessionManager;

public class LoginController implements Controller {

    @Override
    public Response doPost(Request request) {
        try {
            User user = UserService.getUser(request.getBody());
            Response response = Response.redirect("login success","/index.html");
            setSession(user, response);
            setLoginedCookie(response);
            return response;
        } catch (IllegalArgumentException e) {
            return Response.redirect("login failed","/user/login_failed.html");
        }
    }

    private static void setLoginedCookie(Response response) {
        String logined = "logined=true; Path=/";
        response.setCookie(logined);
    }

    private static void setSession(User user, Response response) {
        Session session = SessionManager.createSession();
        session.setAttribute("user", user);
        String sessionId = session.getJSessionCookie();
        response.setCookie(sessionId);
    }

    @Override
    public Response doGet(Request request) {
        String jSessionId = request.getCookies().get("JSESSIONID");
        if (jSessionId == null || SessionManager.findSession(jSessionId) == null) {
            return new StaticResourceController().doGet(request);
        }
        return Response.redirect("already logined", "/index.html");
    }
}
