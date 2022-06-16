package com.ry.a11.cglib;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author ryang
 * @Description
 * @date 2022年06月15日 8:57 上午
 */
public class CglibProxyDemo1 {
    public static void main(String[] args) {
        CglibProxy proxy = new CglibProxy();
        CglibTarget target = new CglibTarget();

        proxy.setMethodInterceptor(new MethodInterceptor() {
            @Override
            public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
                System.out.println("before...");
                // 反射调用
//                Object result = method.invoke(target, args);
                // 内部无反射，结合目标使用
//                Object result = methodProxy.invoke(target, args);
                // 内部无反射，结合代理对象使用
                Object result = methodProxy.invokeSuper(proxy, args);
                System.out.println("after...");
                return result;
            }
        });

        proxy.f1();
        proxy.f2(100);
    }
}
