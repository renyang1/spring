package com.ry.a11.cglib;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.cglib.reflect.FastClass;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author ryang
 * @Description
 * @date 2022年06月08日 5:52 下午
 */
public class CglibProxyDemo {

    public static void main(String[] args) throws IOException {
        CglibTarget target = new CglibTarget();

        Object o = Enhancer.create(CglibTarget.class, new MethodInterceptor() {
            @Override
            public Object intercept(Object p, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                System.out.println("before");
                // 使用反射调用目标方法
//                Object result = method.invoke(target, objects);

                // methodProxy 可以避免反射调用
                // 内部不使用反射，需要目标对象
                Object result = methodProxy.invoke(target, objects);


                // 内部不使用反射，需要代理对象
//                Object result = methodProxy.invokeSuper(p, objects);

//                Method method1 = MethodProxy.class.getDeclaredMethod("getFastClass");
//                method1.setAccessible(true);
//                FastClass fastClass = (FastClass) method1.invoke(methodProxy, new Object[0]);
//                System.out.println(fastClass.getClass());
                System.out.println("after");
//                System.in.read();
                return result;
            }
        });
        System.out.println(o.getClass());
        CglibTarget proxy = (CglibTarget) o;
        proxy.f1();
        proxy.m1();

//        System.in.read();
    }
}
