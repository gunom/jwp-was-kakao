package service;

import java.util.Map;

import db.DataBase;
import model.User;

public class UserService {

    public static User addUser(Map<String, String> requestParams) {
        User user = new User(requestParams);
        DataBase.addUser(user);
        return user;
    }
}
