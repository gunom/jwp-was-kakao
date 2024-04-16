package controller;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import com.github.jknack.handlebars.io.ClassPathTemplateLoader;
import com.github.jknack.handlebars.io.TemplateLoader;
import model.User;
import service.UserService;
import webserver.request.Request;
import webserver.response.Response;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class UserListController implements Controller {

    @Override
    public Response doGet(Request request) {
        if (!checkLogin(request.getCookies())) {
            return Response.redirect("login required","/user/login.html");
        }
        try {
            Collection<User> users = UserService.getUserList();
            return Response.of(rendering(users).getBytes(), "text/html");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Response doPost(Request request) {
        throw new UnsupportedOperationException();
    }

    private static boolean checkLogin(Map<String, String> cookie) {
        return cookie.getOrDefault("logined", "").equals("true");
    }

    private static String rendering(Collection<User> users) throws IOException {
        Map<String, Object> parameterMap = new HashMap<>();
        parameterMap.put("users", users);

        TemplateLoader loader = new ClassPathTemplateLoader();
        loader.setPrefix("/templates");
        loader.setSuffix(".html");
        Handlebars handlebars = new Handlebars(loader);
        Template template = handlebars.compile("/user/list");
        return template.apply(parameterMap);
    }
}
