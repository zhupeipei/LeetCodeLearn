package com.test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ZhuPeipei
 * @date 2021/11/23 16:21
 */
public class ThreadTest {
    private static volatile int num = 1;
    private static final Object lock1 = new Object();
    private static final Object lock2 = new Object();

    public static void main(String[] args) {
        System.out.println("start");
        new Thread(new Consumer()).start();
        new Thread(new Producer()).start();
    }

    private static volatile boolean flag = false;
    private static volatile List<Integer> list = new ArrayList<>();
    private static final Object lock = new Object();
    private static int count = 0;

    static class Consumer implements Runnable {
        @Override
        public void run() {
            while (true) {
                synchronized (lock) {
                    try {
                        while (list.isEmpty()) {
                            lock.wait();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread() + "---" + list.remove(0));
                    if (count > 10) {
                        return;
                    }
                    lock.notify();
                }
            }
        }
    }

    static class Producer implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock) {
                    while (!list.isEmpty()) {
                        lock.notify();
//                        try {
//                            lock.wait();
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
                    }
                    list.add(count++);
                    lock.notify();
                    if (count > 10) {
                        break;
                    }
                }
            }
        }
    }

    public static void main1(String[] args) throws InterruptedException {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
//                Thread.interrupted();
//                finalize();

                while (true) {
                    synchronized (lock1) {
                        try {
                            System.out.println(Thread.currentThread() + "---" + num);
                            num++;

                            lock1.notify();

                            if (num >= 1000) {
                                return;
                            }

                            lock1.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        };

        new Thread(runnable).start();
        Thread.sleep(2000);
        new Thread(runnable).start();
    }


}
