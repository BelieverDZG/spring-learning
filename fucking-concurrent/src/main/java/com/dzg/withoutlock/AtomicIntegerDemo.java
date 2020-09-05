package com.dzg.withoutlock;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntUnaryOperator;

/**
 * 学习cas
 */
public class AtomicIntegerDemo {

    public static void main(String[] args) {
        /*AtomicInteger i = new AtomicInteger(0);

        System.out.println(i.getAndIncrement());//i++
        System.out.println(i);
        System.out.println(i.incrementAndGet());//++i

        System.out.println(i.getAndAdd(5));
        System.out.println(i.addAndGet(5));

        System.out.println(i.get());*/

        AtomicInteger b = new AtomicInteger(10);
        // 读取到     设置值
        System.out.println(b.updateAndGet(operand -> operand + operand));
        updateAndGet(b, operand -> operand * 10);
        System.out.println(b.get());
    }

    /**
     * 模拟updateAndGet的实现
     *
     * @param b
     * @param operator
     */
    private static void updateAndGet(AtomicInteger b, IntUnaryOperator operator) {
        while (true) {
            int cur = b.get();
            int target = operator.applyAsInt(cur);
            if (b.compareAndSet(cur, target)) {
                break;
            }
        }
    }
}
