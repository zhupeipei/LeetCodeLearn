package com.test;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * @author ZhuPeipei
 * @date 2021/11/25 14:55
 */
public class ReferenceTest {
    public static void main(String[] args) throws InterruptedException {
        Object obj = new Object();
        ReferenceQueue<Object> refQueue = new ReferenceQueue<Object>();
        PhantomReference<Object> phantom = new PhantomReference<Object>(obj,
                refQueue);
        System.out.println(phantom.get()); // java.lang.Object@f9f9d8
        System.out.println(refQueue.poll());// null

        obj = null;
        System.gc();

        // 调用phanRef.get()不管在什么情况下会一直返回null
        System.out.println(phantom.get());

        // 当GC发现了虚引用，GC会将phanRef插入进我们之前创建时传入的refQueue队列
        // 注意，此时phanRef所引用的obj对象，并没有被GC回收，在我们显式地调用refQueue.poll返回phanRef之后
        // 当GC第二次发现虚引用，而此时JVM将phanRef插入到refQueue会插入失败，此时GC才会对obj进行回收
        Thread.sleep(200);
        System.out.println(refQueue.poll());
    }
}
