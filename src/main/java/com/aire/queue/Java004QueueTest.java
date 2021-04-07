package com.aire.queue;

import com.google.common.base.Preconditions;

public class Java004QueueTest {

    public static void main(String[] args) {
        MyCircularQueue circularQueue = new MyCircularQueue(3); // 设置长度为 3

        Preconditions.checkState(circularQueue.enQueue(1)); // 返回 true
        Preconditions.checkState(circularQueue.enQueue(2)); // 返回 true
        Preconditions.checkState(circularQueue.enQueue(3)); // 返回 true
        Preconditions.checkState(!circularQueue.enQueue(4)); // 返回 false，队列已满
        Preconditions.checkState(3 == circularQueue.Rear()); // 返回 3
        Preconditions.checkState(circularQueue.isFull()); // 返回 true
        Preconditions.checkState(circularQueue.deQueue()); // 返回 true
        Preconditions.checkState(circularQueue.enQueue(4)); // 返回 true
        Preconditions.checkState(4 == circularQueue.Rear()); // 返回 4
        System.out.println("abs test success");
    }
}

//MyCircularQueue(k): 构造器，设置队列长度为 k 。
//Front: 从队首获取元素。如果队列为空，返回 -1 。
//Rear: 获取队尾元素。如果队列为空，返回 -1 。
//enQueue(value): 向循环队列插入一个元素。如果成功插入则返回真。
//deQueue(): 从循环队列中删除一个元素。如果成功删除则返回真。
//isEmpty(): 检查循环队列是否为空。
//isFull(): 检查循环队列是否已满。
class MyCircularQueue {
    private int size;
    private int head = -1;
    private int tail = -1;
    private int[] data;

    private MyCircularQueue() {
    }

    public MyCircularQueue(int size) {
        this.size = size;
        data = new int[size];
    }

    public boolean enQueue(int val) {
        if (isFull()) {
            return false;
        }
        if (isEmpty()) {
            head = 0;
            data[++tail] = val;
            return true;
        }
        tail = (tail + 1) % size;
        data[tail] = val;
        return true;
    }

    public boolean deQueue() {
        if (isEmpty()) {
            return false;
        }
        data[head] = 0;
        if (head == tail) {
            head = -1;
            tail = -1;
        } else {
            head = (head + 1) % size;
        }
        return true;
    }

    public boolean isEmpty() {
//        if (head == -1 && tail == -1) {
//            return true;
//        }
        return head == -1; // 这里包含了 head=-1
    }

    public boolean isFull() {
        return (tail + 1) % size == head;
    }

    public int Front() {
        if (isEmpty()) {
            return -1;
        } else {
            return data[head];
        }
    }

    public int Rear() {
        if (isEmpty()) {
            return -1;
        } else {
            return data[tail];
        }
    }
}


