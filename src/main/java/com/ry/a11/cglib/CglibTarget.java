package com.ry.a11.cglib;

/**
 * @author ryang
 * @Description
 * @date 2022年06月08日 5:53 下午
 */
public class CglibTarget implements IACglib{

    public void f1() {
        System.out.println("CglibTarget f1");
    }

    public void f2(int i) {
        System.out.println(i);
        System.out.println("CglibTarget f2");
    }

    @Override
    public void m1() {
        System.out.println("CglibTarget m1()...");
    }
}
