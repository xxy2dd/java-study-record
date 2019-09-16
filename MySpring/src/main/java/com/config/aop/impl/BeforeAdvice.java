package com.config.aop.impl;

import com.config.aop.Advice;
import com.config.aop.MethodInvocation;

import java.lang.reflect.Method;

/**
 * @author xxy
 * @date 2019/6/28
 * @description
 */
public class BeforeAdvice implements Advice{
    private Object bean;
    private MethodInvocation methodInvocation;

    public BeforeAdvice(Object bean,MethodInvocation methodInvocation){
        this.bean = bean;
        this.methodInvocation = methodInvocation;
    }

    /**
     * ��д InvocationHandler �ӿ��е�invoke ����
     * @param proxy ����������ʵ��
     * @param method �������ù����������ķ���
     * @param args �÷�����Ҫ�Ĳ���
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable{
        // ��Ŀ�귽��ǰ����֪ͨ
        methodInvocation.invoke();
        // �������÷���
        return method.invoke(bean,args);
    }
}
