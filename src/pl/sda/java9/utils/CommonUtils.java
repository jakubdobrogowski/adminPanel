package pl.sda.java9.utils;

import pl.sda.java9.model.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommonUtils {


    public static Map<Integer, User> createUserMap(List<User> userList) {

        Map<Integer, User> userMap = new HashMap<>();

        for (User user : userList) {

            userMap.put(user.getId(), user);
        }

        return userMap;
    }
}
