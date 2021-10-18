package com.aire.proxy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2021/10/2 3:19 下午.
 *
 * @Author ZhuPeipei
 */
public class UserManager implements IUserManager {
    private List<User> mUsers = new ArrayList<>();

    @Override
    public void addUser(String name) {
        System.out.println("addUser " + name);
        mUsers.add(new User(name));
    }

    @Override
    public User getUserByName(String name) {
        System.out.println("getUserByName " + name);
        for (User user : mUsers) {
            if (user.getName().equals(name)) {
                return user;
            }
        }
        return null;
    }
}
