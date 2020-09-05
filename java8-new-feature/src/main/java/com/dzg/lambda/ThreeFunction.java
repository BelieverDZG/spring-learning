package com.dzg.lambda;

/**
 * 自定义一个函数接口
 * @param <T>
 * @param <U>
 * @param <N>
 * @param <K> 返回元素的类型
 */
@FunctionalInterface
public interface ThreeFunction<T,U,N,K> {

    K apply(T t,U u,N n);
}
