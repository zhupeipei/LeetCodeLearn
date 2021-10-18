package com.test;

/**
 * Created on 2021/10/14 上午1:13.
 *
 * @Author ZhuPeipei
 */
public class Test001 {
    public static void main(String[] args) {
        A a1 = new A();
        A a2 = new B();
        B b = new B();
        C c = new C();
        D d = new D();

        System.out.println("1--" + a1.show(b)); // A and A
        System.out.println("2--" + a1.show(c)); // A and A
        System.out.println("3--" + a1.show(d)); // A and D
        System.out.println("4--" + a2.show(b)); // B and A
        System.out.println("5--" + a2.show(c)); // B and A
        System.out.println("6--" + a2.show(d)); // A and D
        System.out.println("7--" + b.show(b)); // B and B
        System.out.println("8--" + b.show(c)); // B and B
        System.out.println("9--" + b.show(d)); // A and D
        System.out.println("10--" + a2.show(a1)); // B and A
    }


}

class Singleton {
    private static Singleton sSingleton;

    private Singleton() {
    }

    public static Singleton getInstance() {
        if (sSingleton == null) {
            synchronized (Singleton.class) {
                if (sSingleton == null) {
                    sSingleton = new Singleton();
                }
            }
        }
        return sSingleton;
    }
}

class A {
    public String show(D obj) {
        return ("A and D");
    }

    public String show(A obj) {
        return ("A and A");
    }

}

class B extends A {
    public String show(B obj) {
        return ("B and B");
    }

    @Override
    public String show(A obj) {
        return ("B and A");
    }
}

class C extends B {

}

class D extends B {

}