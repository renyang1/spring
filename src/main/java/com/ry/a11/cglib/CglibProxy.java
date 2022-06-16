package com.ry.a11.cglib;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.lang.reflect.UndeclaredThrowableException;

/**
 * @author ryang
 * @Description
 * @date 2022年06月15日 8:58 上午
 */
public class CglibProxy extends CglibTarget{

    private MethodInterceptor methodInterceptor;

    public void setMethodInterceptor(MethodInterceptor methodInterceptor) {
        this.methodInterceptor = methodInterceptor;
    }

    static Method f1;
    static Method f2;
    static MethodProxy f1Proxy;
    static MethodProxy f2Proxy;

    static {
        try {
            f1 = CglibTarget.class.getMethod("f1");
            f2 = CglibTarget.class.getMethod("f2", int.class);
            f1Proxy = MethodProxy.create(CglibTarget.class, CglibProxy.class, "()V", "f1", "f1Super");
            f2Proxy = MethodProxy.create(CglibTarget.class, CglibProxy.class, "(I)V", "f1", "f2Super");
        } catch (NoSuchMethodException e) {
            throw new NoSuchMethodError(e.getMessage());
        }
    }

    public void f1Super() {
        super.f1();
    }

    public void f2Super(int i) {
        super.f2(i);
    }

    @Override
    public void f1() {
        try {
            methodInterceptor.intercept(this, f1, new Object[0], f1Proxy);
        } catch (Throwable e) {
            throw new UndeclaredThrowableException(e);
        }
    }

    @Override
    public void f2(int i) {
        try {
            Object[] objects = new Object[1];
            objects[0] = i;
            methodInterceptor.intercept(this, f2, objects, f2Proxy);
        } catch (Throwable e) {
            throw new UndeclaredThrowableException(e);
        }
    }

}
