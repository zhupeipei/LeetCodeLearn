package com.test;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author ZhuPeipei
 * @date 2021/11/25 15:43
 */
public class ThreadTest002 {
    private static ReentrantLock lock = new ReentrantLock();
    private static ReentrantLock lock2 = new ReentrantLock();
    private static volatile int count = 0;

    public static void main(String[] args) {
        new Thread(new ThreadRunnable()).start();
        new Thread(new ThreadRunnable2()).start();
    }

    static class ThreadRunnable implements Runnable {
        @Override
        public void run() {
            while (count < 10) {
                lock.lock();

            }
        }
    }

    static class ThreadRunnable2 implements Runnable {
        @Override
        public void run() {
            while (count < 10) {
                lock.unlock();

            }
        }
    }

    static class aaa extends AbstractQueuedSynchronizer {
        @Override
        protected boolean tryAcquire(int arg) {
            return super.tryAcquire(arg);
        }

        @Override
        protected boolean tryRelease(int arg) {
            return super.tryRelease(arg);
        }

        @Override
        protected boolean tryReleaseShared(int arg) {
            return super.tryReleaseShared(arg);
        }
    }
}
