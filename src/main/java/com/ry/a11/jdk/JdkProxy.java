package com.ry.a11.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.NoSuchElementException;

/**
 * @author ryang
 * @Description
 * @date 2022年06月08日 7:39 下午
 */
public class JdkProxy extends Proxy implements IA {

    protected JdkProxy(InvocationHandler h) {
        super(h);
    }

    private static Method f1;
    static {
        try {
            f1 = Class.forName("com.ry.a11.jdk.IA").getMethod("f1");
        } catch (NoSuchMethodException | ClassNotFoundException e) {
            throw new NoSuchElementException(e.getMessage());
        }
    }

    @Override
    public void f1() {
        try {
            h.invoke(this, f1, new Object[0]);
        } catch (Throwable e) {
            throw new UndeclaredThrowableException(e);
        }
    }
}
