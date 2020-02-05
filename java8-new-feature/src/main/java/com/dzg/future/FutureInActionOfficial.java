package com.dzg.future;

import java.util.concurrent.*;

public class FutureInActionOfficial {

    public static void main(String[] args)
            throws ExecutionException,
            InterruptedException, TimeoutException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<String> str = executorService.submit(()->{
            try {
                Thread.sleep(5000);
                return "I am finished";
            } catch (InterruptedException e) {
                return "I am wrong";
            }
        });
        /*
        * TODO
        *  TODO
        * */
//        System.out.println(str);
//        System.out.println(str.get());//线程阻塞五秒后返回值I am finished
        String value = str.get(3, TimeUnit.SECONDS);//等待时间超过三秒后则不再等待
        while(!str.isDone()){
            Thread.sleep(10);
        }
        System.out.println(value);
        executorService.shutdown();//关闭后，当前线程才会真正的结束
    }
}
