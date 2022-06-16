package com.ry.a11.cglib;

import org.springframework.cglib.core.Signature;

/**
 * 实际上动态生成的 TargetFastClass 会继承 FastClass，这里模拟动态生成的类为了少重写方法，故未实现
 * @author ryang
 * @Description
 * @date 2022年06月15日 4:50 下午
 */
public class TargetFastClass {
    static Signature s0 = new Signature("f1", "()V");
    static Signature s1 = new Signature("f2", "(I)V");


    /**
     * 获取目标方法的编号 f1()->0、f2(int i)->1
     *
     * @author ryang
     * @date 2022/6/15 8:25 下午
     * @param signature
     * @return null
     */
    public int getIndex(Signature signature) {
        if (s0.equals(signature)) {
            return 0;
        }else if (s1.equals(signature)) {
            return 1;
        }
        else {
            return -1;
        }
    }

    /**
     * 根据方法编号，用原对象不使用反射调用目标对象方法
     *
     * @author ryang
     * @date 2022/6/15 8:25 下午
     * @param index
     * @param target
     * @param args
     * @return java.lang.Object
     */
    public Object invoke(int index, Object target, Object[] args) {
        if (index == 0) {
            ((CglibTarget) target).f1();
            return null;
        } else if(index == 1) {
            ((CglibTarget) target).f2((Integer) args[0]);
            return null;
        }else {
            throw new NoSuchMethodError("无此方法");
        }
    }

    public static void main(String[] args) {
        TargetFastClass fastClass = new TargetFastClass();
        int index = fastClass.getIndex(new Signature("f1", "()V"));
        Object result = fastClass.invoke(index, new CglibTarget(), new Object[0]);
    }
}
