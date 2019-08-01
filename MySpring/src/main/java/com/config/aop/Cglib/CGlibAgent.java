package com.config.aop.Cglib;

import com.config.aop.MethodInvocation;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author xxy
 * @date 2019/8/1
 * @description
 */
public class CGlibAgent implements MethodInterceptor {
    private Object proxy;
    public Object getInstance(Object proxy){
        this.proxy = proxy;
        Enhancer enhancer = new Enhancer();
        // ��������
        enhancer.setSuperclass(this.proxy.getClass());
        // �ص�����
        enhancer.setCallback(this);
        return enhancer.create();

    }

    /**
     * �ص�����
     * @param obj
     * @param method
     * @param objects
     * @param methodProxy
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object obj, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
       System.out.println("before invoking");
       // ��������
       Object ret = methodProxy.invokeSuper(obj,objects);
       System.out.println("after invoking");
       return ret;
    }
}
