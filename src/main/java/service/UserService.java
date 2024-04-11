package service;

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
}
