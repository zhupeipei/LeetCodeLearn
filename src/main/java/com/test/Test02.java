package com.test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ZhuPeipei
 * @date 2021/11/10 19:40
 */
public class Test02 {
    public static void main(String[] args) {
        Erasure<String> erasure = new Erasure<String>("hello");
        Class eclz = erasure.getClass();
        System.out.println("erasure class is:" + eclz.getName());

        Method[] methods = eclz.getDeclaredMethods();
        for (Method m : methods) {
            System.out.println(" method:" + m.toString());
        }

        List<?>[] list = new ArrayList<?>[3];
        List<String>[] list1 = new ArrayList[4];

        List[] arr = new List[5];
    }

}

class Erasure<T extends String> {
    T object;

    public Erasure(T object) {
        this.object = object;
    }

    public void add(T object) {

    }

}