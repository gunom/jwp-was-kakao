package service;

import java.util.Collection;
import java.util.Map;

import db.DataBase;
import model.User;

public class UserService {

    public static User addUser(Map<String, String> requestParams) {
        validateParams(requestParams);
        User user = new User(requestParams);
        DataBase.addUser(user);
        return user;
    }

    private static void validateParams(Map<String, String> requestParams) {
        if (requestParams.get("userId") == null || requestParams.get("password") == null || requestParams.get("name") == null || requestParams.get("email") == null) {
            throw new IllegalArgumentException("회원 정보가 올바르지 않습니다.");
        }
    }

    public static User getUser(Map<String, String> body) {
        if (body.get("userId") == null) {
            throw new IllegalArgumentException("회원 정보가 올바르지 않습니다.");
        }
        User user = DataBase.findUserById(body.get("userId"));
        if (user == null) {
            throw new IllegalArgumentException("존재하지 않는 회원입니다.");
        }
        if (!user.getPassword().equals(body.get("password"))) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        return user;
    }

    public static Collection<User> getUserList() {
        return DataBase.findAll();
    }
}
