package com.test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ZhuPeipei
 * @date 2021/11/24 22:47
 */
public class ConsumerProducer1 {
    // 一个生产者 一个消费者 生产一个消费一个
    public static void main(String[] args) {
        new Thread(new Consumer()).start();
        new Thread(new Producer()).start();
    }

    private static final List<Integer> sList = new ArrayList();
    private static Object lock = new Object();

    static class Consumer implements Runnable {
        @Override
        public void run() {
            System.out.println("进入消费者线程");
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock) {
                    while (sList.isEmpty()) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println(sList.size() + ", 获取数据: " + sList.remove(0));
                    lock.notifyAll();
                }
            }
        }
    }

    static class Producer implements Runnable {
        @Override
        public void run() {
            System.out.println("进入生产者线程");
            int count = 0;
            while (count++ < 10) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock) {
                    while (!sList.isEmpty()) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    sList.add(count);
                    lock.notifyAll();
                }
            }
            System.out.println("生产结束");
        }
    }
}
