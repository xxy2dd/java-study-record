package com.test;

import com.config.SimpleAOP;
import com.config.aop.Advice;
import com.config.aop.MethodInvocation;
import com.config.aop.impl.BeforeAdvice;
import com.service.HelloService;
import com.service.impl.HelloServiceImpl;

/**
 * @author xxy
 * @date 2019/7/11
 * @description
 */
public class SimpleAOPTest {
    public static void main(String[] args){
        // ����һ�� MethodInvocation ʵ����
        MethodInvocation logTask = ()->System.out.println("log task start");
        HelloServiceImpl helloServiceImpl = new HelloServiceImpl();
        // ����һ��Advice
        Advice beforeAdvice = new BeforeAdvice(helloServiceImpl,logTask);
        // ΪĿ��������ɴ���
        HelloService helloServiceImplProxy = (HelloService) SimpleAOP.getProxy(helloServiceImpl,beforeAdvice);
        helloServiceImplProxy.sayHelloWorld();

    }
}
