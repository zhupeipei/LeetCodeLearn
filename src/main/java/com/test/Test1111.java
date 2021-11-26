package com.test;

/**
 * @author ZhuPeipei
 * @date 2021/11/19 17:55
 */
public class Test1111 {

    static class A {
        void method1() {
            System.out.println("abc");
        }
    }

    static class B extends A {
        @Override
        void method1() {
            super.method1();
            System.out.println("qwe");
        }
    }

    public static void main(String[] args) {
        A b = new B();
        b.method1();

        new Thread(new XT()).start();
    }

    static class XT extends Thread {
        @Override
        public void run() {
            super.run();
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
