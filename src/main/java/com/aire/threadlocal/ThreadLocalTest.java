package com.aire.threadlocal;

/**
 * Created on 2021/10/5 4:36 下午.
 *
 * @Author ZhuPeipei
 */
public class ThreadLocalTest {
    public static void main(String[] args) {
        new Thread(new ThreadTest1()).start();
    }

    static class ThreadTest1 implements Runnable {
        private ThreadLocal<String> strLocal = new ThreadLocal() {
            @Override
            protected Object initialValue() {
                return "abc";
            }
        };

        @Override
        public void run() {
            int i = 0;

            while (true) {
                System.out.println(strLocal.get());
                strLocal.set(strLocal.get() + i++);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
