package com.test;

import java.io.*;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created on 2021/9/23 9:11 下午.
 *
 * @Author ZhuPeipei
 */
public class MappedByteBufferDemo {
    public static void main(String[] args) throws Exception {
        /**
         * output: 0.001s(读)
         * input: 0.11s(写)
         * */
        MappedByteBufferTest();
        /**
         * size=1024*8
         * out: 0.0s
         * input: 0.014s
         * */
        /**
         * size=1024*1024*8
         * output: 0.01s
         * input: 0.014s
         * */
        /**
         * size=80
         * output: 0.0s
         * input: 0.546s
         * */
        BufferTest();
        /**
         * time: 0.585s
         * */
        //BufferedInputStreamTest();
    }

    /*
     * 测试结果与Buffer size有关
     */
    // 1、使用MappedByteBuffer: 0.7s
    public static void MappedByteBufferTest() throws Exception {
        String srcFile = "/Users/xmly/spzm/LeetCodeLearn/src/main/assets/get-pip.py";
        String destFile = "/Users/xmly/spzm/LeetCodeLearn/src/main/assets/get-pip_mywrite_test.txt";
        RandomAccessFile rafi = new RandomAccessFile(srcFile, "r");
        RandomAccessFile rafo = new RandomAccessFile(destFile, "rw");
        FileChannel fci = rafi.getChannel();
        FileChannel fco = rafo.getChannel();
        long size = fci.size();
        byte b;
        long start = System.currentTimeMillis();
        MappedByteBuffer mbbi = fci.map(FileChannel.MapMode.READ_ONLY, 0, size);
        System.out.println("MappedByteBufferTest output: " + (double) (System.currentTimeMillis() - start) / 1000 + "s");
        MappedByteBuffer mbbo = fco.map(FileChannel.MapMode.READ_WRITE, 0, size);
        start = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            b = mbbi.get(i);
            mbbo.put(i, b);
        }
        fci.close();
        fco.close();
        rafi.close();
        rafo.close();
        System.out.println("MappedByteBufferTest input: " + (double) (System.currentTimeMillis() - start) / 1000 + "s");
    }

    // 2、自己处理Buffer(RandomAccessFile): 0.13s
    public static void BufferTest() throws Exception {
        String srcFile = "/Users/xmly/spzm/LeetCodeLearn/src/main/assets/get-pip.py";
        String destFile = "/Users/xmly/spzm/LeetCodeLearn/src/main/assets/get-pip_test_buffer_test.txt";
        RandomAccessFile rafi = new RandomAccessFile(srcFile, "r");
        RandomAccessFile rafo = new RandomAccessFile(destFile, "rw");

        byte[] buf = new byte[80];
        long start = System.currentTimeMillis();
        int c = rafi.read(buf);
        System.out.println("BufferTest output: " + (double) (System.currentTimeMillis() - start) / 1000 + "s");
        start = System.currentTimeMillis();
        while (c > 0) {
            if (c == buf.length) {
                rafo.write(buf);
            } else {
                rafo.write(buf, 0, c);
            }

            c = rafi.read(buf);
        }
        System.out.println("BufferTest input: " + (double) (System.currentTimeMillis() - start) / 1000 + "s");
        rafi.close();
        rafo.close();

    }

    // 3、BufferedInputStream&BufferedOutputStream: 3.02s
    public static void BufferedInputStreamTest() throws Exception {
        String srcFile = "F:\\Ebook\\偷天.txt";
        String destFile = "F:\\Ebook\\toutian.txt";
        FileInputStream rafi = new FileInputStream(srcFile);
        FileOutputStream rafo = new FileOutputStream(destFile);

        BufferedInputStream bis = new BufferedInputStream(rafi, 8192);
        BufferedOutputStream bos = new BufferedOutputStream(rafo, 8192);
        long size = rafi.available();

        long start = System.currentTimeMillis();

        for (int i = 0; i < size; i++) {
            byte b = (byte) bis.read();
            bos.write(b);
        }
        rafi.close();
        rafo.close();
        System.out.println("time: " + (double) (System.currentTimeMillis() - start) / 1000 + "s");

    }
}
