package com.aire.proxy;

/**
 * Created on 2021/10/2 3:17 下午.
 *
 * @Author ZhuPeipei
 */
public interface IUserManager {
    void addUser(String name);

    User getUserByName(String name);
}
