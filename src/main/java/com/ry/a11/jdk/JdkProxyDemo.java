package com.ry.a11.jdk;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author ryang
 * @Description
 * @date 2022年06月08日 11:31 上午
 */
public class JdkProxyDemo {
    public static void main(String[] args) throws IOException {
        // 目标对象
        ATarget target = new ATarget();

        // 类加载器，用来加载在运行期间动态生成的字节码
        ClassLoader classLoader = JdkProxyDemo.class.getClassLoader();

        Object proxy = Proxy.newProxyInstance(classLoader, target.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("before...");
                Object invoke = method.invoke(target, args);
                System.out.println("after");
                return invoke;
            }
        });

        // class com.sun.proxy.$Proxy0
        System.out.println(proxy.getClass());

//        IA ia = (IA) proxy;
//        ia.f1();
//
        JdkTarget jdkTarget = (JdkTarget) proxy;
        jdkTarget.f1();
//        System.in.read();

    }
}
