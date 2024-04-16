package service;

import java.security.InvalidParameterException;
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
        if (requestParams.get("userId") == null || requestParams.get("password") == null ||
                requestParams.get("name") == null || requestParams.get("email") == null) {
            throw new InvalidParameterException("회원 정보가 올바르지 않습니다.");
        }
    }

    public static User getUser(Map<String, String> body) {
        checkForMissingUserId(body);
        User user = DataBase.findUserById(body.get("userId"));
        if (user == null) {
            throw new InvalidParameterException("회원 정보가 올바르지 않습니다.");
        }
        verifyUserPassword(user, body.get("password"));
        return user;
    }

    private static void checkForMissingUserId(Map<String, String> body) {
        if (body.get("userId") == null) {
            throw new InvalidParameterException("회원 정보가 올바르지 않습니다.");
        }
    }

    private static void verifyUserPassword(User user, String password) {
        if (!user.getPassword().equals(password)) {
            throw new InvalidParameterException("비밀번호가 일치하지 않습니다.");
        }
    }

    public static Collection<User> getUserList() {
        return DataBase.findAll();
    }
}
