package com.ry.a11.cglib;

import org.springframework.cglib.core.Signature;

/**
 * @author ryang
 * @Description
 * @date 2022年06月15日 4:43 下午
 */
public class ProxyFastClass {

    static Signature f1 = new Signature("f1", "()V");
    static Signature f2 = new Signature("f2", "(I)V");

    public int getIndex(Signature signature) {
        if (f1.equals(signature)) {
            return 0;
        }else if (f2.equals(signature)) {
            return 1;
        }else {
            return -1;
        }
    }

    /**
     * 根据方法编号，用代理对象不用反射调用目标对象方法
     * @author ryang
     * @date 2022/6/15 10:02 下午
     * @param index
     * @param proxy
     * @param args
     * @return java.lang.Object
     */
    public Object invoke(int index, Object proxy, Object[] args) {
        if (index == 0) {
            // 代理对象需要调用代理类中新增的直接调用父类的方法
            ((CglibProxy)proxy).f1Super();
            return null;
        } else if (index == 1) {
            ((CglibProxy)proxy).f1Super();
            return null;
        }else {
            throw new NoSuchMethodError("无此方法");
        }
    }

    public static void main(String[] args) {
        ProxyFastClass fastClass = new ProxyFastClass();
        int index = fastClass.getIndex(new Signature("f1", "()V"));
        Object result = fastClass.invoke(index, new CglibProxy(), new Object[0]);
    }
}
