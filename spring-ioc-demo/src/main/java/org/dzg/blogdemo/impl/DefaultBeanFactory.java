package org.dzg.blogdemo.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dzg.blogdemo.BeanDefinition;
import org.dzg.blogdemo.BeanDefinitionRegistry;
import org.dzg.blogdemo.BeanFactory;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.io.Closeable;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultBeanFactory implements BeanFactory, BeanDefinitionRegistry, Closeable {

    private Log log = LogFactory.getLog(this.getClass());

    //ConCurrentHashMap应对并发编程
    private Map<String,BeanDefinition> bdMap = new ConcurrentHashMap<>();
    private Map<String,Object> beanMap = new ConcurrentHashMap<>();


    @Override
    public void register(BeanDefinition bd, String beanName) {
//        Assert.notNull();
        Assert.notNull("BeanName不能为空！",beanName);
        Assert.notNull("BeanDefinition不能为空！",bd.toString());

//        Assert.assertNotNull("BeanName不能为空！",beanName);
//        Assert.assertNotNull("BeanDefinition不能为空！",bd);

        if (bdMap.containsKey(beanName)){
            log.info("{"+beanName+"}"+"已经存在！");
        }

        if (!bd.validate()){
            log.info("BeanDefinition不合法！");
        }

        if (!bdMap.containsKey(beanName)){
            bdMap.put(beanName,bd);
        }
    }

    @Override
    public BeanDefinition getBeanDefinition(String beanName) {
        if (!bdMap.containsKey(beanName)){
            log.info("{"+beanName+"}"+"不存在！");
        }
        return bdMap.get(beanName);
    }

    @Override
    public boolean containsBeanDefinition(String beanName) {
        return bdMap.containsKey(beanName);
    }


    public Object doGetBean(String beanName) {
        if (!beanMap.containsKey(beanName)){
            log.info("{"+beanName+"}"+"不存在！");
        }
        Object instance = beanMap.get(beanName);
        if (instance != null){
            return instance;
        }
        //不存在则进行创建
        if (!this.bdMap.containsKey(beanName)){
            log.info("名为"+"{"+beanName+"}"+"的bean定义不存在！");
        }
        BeanDefinition bd = this.bdMap.get(beanName);
        Class<?> beanClass = bd.getBeanClass();
        if (beanClass != null){
            instance = crateBeanByConstruct(beanClass);
            if (instance == null){
                instance = createBeanByStaticFactoryMethod(bd);
            }
        }else if(instance == null && StringUtils.isEmpty(bd.getStaticCreateBeanMethod())){// StringUtils.isNotBlank(bd.getStaticCreateBeanMethod()
            instance = createBeanByFactoryMethod(bd);
        }
        return instance;
    }

    private void doInit(BeanDefinition bd,Object instance){
        Class<?> clazz = instance.getClass();
        if(StringUtils.isEmpty(bd.getBeanInitMethod())){
            try {
                Method method = clazz.getMethod(bd.getBeanInitMethod(),null);
                method.invoke(instance,null);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * 静态方法创建实例
     * @param bd
     * @return
     */
    private Object createBeanByStaticFactoryMethod(BeanDefinition bd) {
        Object instance = null;
        try {
            Class<?> beanClass = bd.getBeanClass();
            //获取创建实例的方法
            Method method = beanClass.getMethod(bd.getStaticCreateBeanMethod());
            instance = method.invoke(beanClass,null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return instance;
    }

    /**
     * 普通工厂方法获取实例
     * @return
     */
    private Object createBeanByFactoryMethod(BeanDefinition bd) {
        Object instance = null;
        try {
            //获取工厂类
            Object factory = doGetBean(bd.getBeanFactory());
            //获取创建实例的方法
            Method method = factory.getClass().getMethod(bd.getBeanCreateMethod());
            //执行方法
            instance = method.invoke(factory,null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return instance;
    }

    /**
     * 更具构造方法创建实例
     * @param beanClass
     * @return
     */
    private Object crateBeanByConstruct(Class<?> beanClass) {
        Object instance = null;
        try {
            instance = beanClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return instance;
    }

    @Override
    public Object getBean(String beanName) {
        if (!bdMap.containsKey(beanName)){
            log.info("不存在"+beanName);
        }
        return bdMap.get(beanName);
    }

    @Override
    public boolean registerBeanPostProcessor(BeanPostProcessor processor) {
        return false;
    }

    @Override
    public void close() throws IOException {
        Set<Map.Entry<String,BeanDefinition>> entries = bdMap.entrySet();
        for (Map.Entry<String,BeanDefinition> entry:
             entries) {
            BeanDefinition value = entry.getValue();
            String destroyMethodName = value.getBeanDestroyMethod();
            try {
                Method method = value.getBeanClass().getMethod(destroyMethodName,null);
                method.invoke(value.getBeanClass(),null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
