package com.test;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author ZhuPeipei
 * @date 2021/11/5 15:55
 */
public class TryTest {
    public static void main(String[] args) {
        new TryTest().tryPerformance();

//        List<String> l1 = new ArrayList<String>();
//        List<Integer> l2 = new ArrayList<Integer>();
//
//        System.out.println(l1.getClass() == l2.getClass());
//        System.out.println(l1.getClass());
//        System.out.println(l1.getClass().getTypeName());
    }

    public static void main1(String[] args) {
//        System.out.println(new TryTest().tryTest());
//        System.out.println(new TryTest().tryTest1());
        new TryTest().tryPerformance();

        try {
            new TryTest().clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return null;
    }

    private static class TryObject {
        int num = 0;
    }

    private int tryTest1() {
        TryObject obj = new TryObject();
        try {
            System.out.println("abc test " + (++obj.num));
            return (++obj.num);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("finally " + (++obj.num));
            return obj.num;
        }
    }

    private int tryTest() {
        try {
            System.out.println("abc");
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 2;
        } finally {
            int i = 1 / 0;
            return 3;
        }
//        return 4;
    }

    private void tryPerformance() {
        long curTime;
        int times = 10000;

        curTime = System.currentTimeMillis();

        for (int i = 0; i < times; i++) {
            getSimpleTrace();
        }

        System.out.println("simple: " + (System.currentTimeMillis() - curTime));

        curTime = System.currentTimeMillis();

        for (int i = 0; i < times; i++) {
            getTrace();
        }

        System.out.println("catch: " + (System.currentTimeMillis() - curTime));
    }

    private int getSimpleTrace() {
        int time = 0;
        time++;
        return time;
    }

    private int getStackTrace() {
        try {
            int time = 0;
            time++;
            return time;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    private void getTrace() {
        Throwable tr = new Throwable();

        int maxDepth = 6;
        StringBuilder sb = new StringBuilder();
        sb.append(tr.toString()).append("\n");
        StackTraceElement[] stackTrace = tr.getStackTrace();
        int size = stackTrace.length > maxDepth ? maxDepth : stackTrace.length;
        for (int i = 0; i < size; i++) {
            StackTraceElement element = stackTrace[i];
            sb.append(element.getClassName() + ", " + element.getLineNumber() + ", " + element.getMethodName()).append("\n");
        }
        sb.append("\n\n\n");
        System.out.println(sb);
//
//        sb.append("\n\n\n");
//        System.out.println(getStackTraceString(new Throwable("hahah")));


//        while (t != null && size > 0) {
//            if (t instanceof UnknownHostException) {
//                break;
//            }
//            t = t.getCause();
//            size--;
//        }
    }

    private static class MyThrowable extends Throwable {
        private void my() {
            getStackTrace();
        }
    }

    public static String getStackTraceString(Throwable tr) {
        if (tr == null) {
            return "";
        }

        // This is to reduce the amount of log spew that apps do in the non-error
        // condition of the network being unavailable.
        Throwable t = tr;
        while (t != null) {
            if (t instanceof UnknownHostException) {
                return "";
            }
            t = t.getCause();
        }

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        tr.printStackTrace(pw);
        pw.flush();
        return sw.toString();
    }
}
