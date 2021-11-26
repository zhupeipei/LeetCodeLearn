package com.test;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author ZhuPeipei
 * @date 2021/11/25 16:21
 */
public class DeadThread {
    private static Object lock = new Object();
    private static ReentrantLock lock1 = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new ThreadRunnable(null), "thread-1");
        Thread t2 = new Thread(new ThreadRunnable(t1), "thread-2");
        Thread t3 = new Thread(new ThreadRunnable(t2), "thread-3");

        System.out.println("asdasdasd");

        lock1.lock();

        t3.start();
        Thread.sleep(10);
        t2.start();
        Thread.sleep(10);
        t1.start();

        Thread.sleep(1000);
        lock1.newCondition().notify();

        lock1.unlock();
    }

    static class ThreadRunnable implements Runnable {
        private Thread preThread;

        public ThreadRunnable(Thread preThread) {
            this.preThread = preThread;
        }

        @Override
        public void run() {
            lock1.lock();
            if (preThread != null) {
                try {
                    preThread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName());
            lock1.unlock();
        }
    }
}
