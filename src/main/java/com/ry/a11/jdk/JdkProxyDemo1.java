package com.ry.a11.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author ryang
 * @Description
 * @date 2022年06月08日 7:57 下午
 */
public class JdkProxyDemo1 {
    public static void main(String[] args) {
        IA ia = new ATarget();

        // 创建代理对象
        JdkProxy proxy = new JdkProxy(new InvocationHandler() {// 匿名内部内不能定义构造函数
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("before...");
                // 调用目标
                Object result = method.invoke(ia, args);
                System.out.println("after...");
                return result;
            }
        });

        // 通过代理对象调用目标方法
        proxy.f1();
    }
}
