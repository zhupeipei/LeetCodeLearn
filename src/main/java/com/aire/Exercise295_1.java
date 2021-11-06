package com.aire;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author ZhuPeipei
 * @date 2021/11/6 22:41
 */
public class Exercise295_1 {
    public static void main(String[] args) {

    }

    // big所有值都大于small
    private PriorityQueue<Integer> mBigQueue = new PriorityQueue<>(); // 默认顶端最小值
    private PriorityQueue<Integer> mSmallQueue = new PriorityQueue<>(new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
        }
    });

    public void addNum(int num) {
        int bigSize = mBigQueue.size();
        int smallSize = mSmallQueue.size();
        if (bigSize == 0) {
            if (smallSize == 0) {
                mBigQueue.offer(num);
            } else {
                int smallVal = mSmallQueue.peek();
                if (smallVal > num) {
                    mBigQueue.offer(mSmallQueue.poll());
                    mSmallQueue.offer(num);
                }
            }
        } else if (smallSize == 0) {
            int bigVal = mBigQueue.peek();
            if (num > bigVal) {
                mSmallQueue.offer(mBigQueue.poll());
                mBigQueue.offer(num);
            } else {
                mSmallQueue.offer(num);
            }
        } else {
            if (bigSize > smallSize) {
                int bigVal = mBigQueue.peek();
                if (num > bigVal) {
                    mSmallQueue.offer(mBigQueue.poll());
                    mBigQueue.offer(num);
                } else {
                    mSmallQueue.offer(num);
                }
            } else if (bigSize < smallSize) {
                int smallVal = mSmallQueue.peek();
                if (num < smallVal) {
                    mBigQueue.offer(mSmallQueue.poll());
                    mSmallQueue.offer(num);
                } else {
                    mBigQueue.offer(num);
                }
            } else {
                int smallVal = mSmallQueue.peek();
                if (num < smallVal) {
                    mSmallQueue.offer(num);
                } else {
                    mBigQueue.offer(num);
                }
            }
        }
    }

    public double findMedian() {
        int bigSize = mBigQueue.size();
        int smallSize = mSmallQueue.size();
        if (bigSize == smallSize) {
            return (mBigQueue.peek() + mSmallQueue.peek()) / 2f;
        } else if (bigSize > smallSize) {
            return mBigQueue.peek();
        } else {
            return mSmallQueue.peek();
        }
    }
}
