package com.test;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author ZhuPeipei
 * @date 2021/11/26 15:29
 */
public class ThreadTest003 {
    private static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(() -> {
                lock.lock();
                try {
                    Thread.sleep(10000);
                    int oi = 0;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            });
            thread.start();
        }
    }
}
