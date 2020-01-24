package com.dzg.utils;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * 用于记录日志的工具类
 */
public class Logger {

    /**
     * 用于打印日志，计划让其在切入点方法执行之前执行（切入点方法就是业务层的方法
     * 前置通知
     */
    public void printLogger(){
        System.out.println("logger类中的printlog方法开始记录日志。。。。");
    }

    /**
     * 后置通知
     */
    public void afterReturningPrint(){
        System.out.println("后置通知:logger类中的afterReturningPrint方法开始记录日志。。。");
    }

    /**
     * 异常通知
     */
    public void throwingPrint(){
        System.out.println("异常通知:logger类中的throwingPrint方法开始记录日志。。。");
    }

    /**
     * 最终通知
     */
    public void finalPrint(){
        System.out.println("最终通知:logger类中的finalPrint方法开始记录日志。。。");
    }

    /**
     * 环绕通知
     * @param pjp
     * @return
     */
    public Object aroundPrint(ProceedingJoinPoint pjp){
        Object rtValue = null;

        try {
            Object[] args = pjp.getArgs();//得到方法执行的参数
            System.out.println("环绕通知：logger类中的aroundPrint方法开始记录日志。。。前置通知");

            //明确调用业务层的方法-切入点的方法
            rtValue = pjp.proceed();
            System.out.println("环绕通知：logger类中的aroundPrint方法开始记录日志。。。后置通知");

            return rtValue;
        } catch (Throwable throwable) {
            System.out.println("环绕通知：logger类中的aroundPrint方法开始记录日志。。。异常通知");
            throw new RuntimeException(throwable);//抛出运行时异常后，不用在方法的最后返回值
        }finally {
            System.out.println("环绕通知：logger类中的aroundPrint方法开始记录日志。。。最终通知");
        }

//        return rtValue;
    }
}
