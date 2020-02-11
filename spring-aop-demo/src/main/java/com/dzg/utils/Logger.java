package com.dzg.utils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

/**
 * 用于记录日志的工具类
 *
 * 2020-02-11 : 将XML的形式改为基于注解的方式是使用AOP功能
 *              1）在beans.xml中添加<aop:aspectj-autoproxy></aop:aspectj-autoproxy>
 *              或者是 创建一个配置类 @EnableAspectjAutoProxy
 *              2)在对应的类上添加@Aspect注解，表明该类为一个切面
 *              3）在对应的方法上使用@Before、@After等注解，注解默认值为 value="execution表达式"，用于绑定通知
 */
@Aspect
public class Logger {

    @Pointcut(value = "execution(* com.dzg.service.impl.*.*(..))")
    public void execExpression(){}
    /**
     * 用于打印日志，计划让其在切入点方法执行之前执行（切入点方法就是业务层的方法
     * 前置通知
     */
//    @Before("execution(* com.dzg.service.impl.*.*(..))")
    @Before("execExpression()")
    public void printLogger() {
        System.out.println("logger类中的printlog方法开始记录日志。。。。");
    }

    /**
     * 返回通知
     */
//    @AfterReturning("execution(* com.dzg.service.impl.*.*(..))")
    @AfterReturning("execExpression()")
//    @AfterReturning(returning = "")
    public void afterReturningPrint() {
        System.out.println("返回通知:logger类中的afterReturningPrint方法开始记录日志。。。");
    }

    /**
     * 异常通知
     */
//    @AfterThrowing(value = "execution(* com.dzg.service.impl.*.*(..))")
    @AfterThrowing(value = "execExpression()")
    public void throwingPrint() {
        System.out.println("异常通知:logger类中的throwingPrint方法开始记录日志。。。");
    }

    /**
     * 后置通知
     */
//    @After(value = "execution(* com.dzg.service.impl.*.*(..))")
    @After(value = "execExpression()")
    public void finalPrint() {
        System.out.println("后置通知:logger类中的finalPrint方法开始记录日志。。。");
    }

    /**
     * 环绕通知
     *
     * @param pjp
     * @return
     */
//    @Around(value = "execution(* com.dzg.service.impl.*.*(..))")
    public Object aroundPrint(ProceedingJoinPoint pjp) {
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
        } finally {
            System.out.println("环绕通知：logger类中的aroundPrint方法开始记录日志。。。最终（后置）通知");
        }

//        return rtValue;
    }
}
