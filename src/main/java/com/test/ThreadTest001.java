package com.test;

/**
 * @author ZhuPeipei
 * @date 2021/11/24 23:55
 */
public class ThreadTest001 {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (i < 1000000000) {
                    i++;
                    StringBuffer sb = new StringBuffer();
                    sb.append("asdasdas");
                }
                System.out.println("2, " + System.currentTimeMillis());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
        Thread.sleep(10);
        t.interrupt();
        System.out.println("1, " + System.currentTimeMillis());
    }
}
