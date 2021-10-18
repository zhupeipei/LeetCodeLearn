package com.aire.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created on 2021/10/2 3:22 下午.
 *
 * @Author ZhuPeipei
 */
public class ProxyTest {
    public static void main(String[] args) {
        UserManager target = new UserManager();
        IUserManager proxy = (IUserManager) Proxy.newProxyInstance(UserManager.class.getClassLoader(), UserManager.class.getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("abc");
                return method.invoke(target, args);
            }
        });
        proxy.addUser("jack");
        proxy.getUserByName("jack");
    }
}
