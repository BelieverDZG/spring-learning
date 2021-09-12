package com.dzg.thread.pattern;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * 一个线程等待另一个线程的执行结果
 */
public class GuardedSuspension {

    public static void main(String[] args) {
        GuardedObject guardedObject = new GuardedObject();
        new Thread(() ->{
            System.out.println(Thread.currentThread().getName()+  "等待结果。。。");
            List<String> list = (List<String>) guardedObject.get(2000);
            System.out.println(list.size());
        },"t1").start();

        new Thread(() ->{
            System.out.println(Thread.currentThread().getName()+ "执行下载。。。");
            List<String> list = null;
            try {
                list = Downloader.download();
            } catch (IOException e) {
                e.printStackTrace();
            }
            guardedObject.complete(list);
        },"t2").start();
    }
}

class GuardedObject{
    //res
    private Object response;

    //get result
    public Object get(long timeout){
        synchronized (this){

            //开始时间
            long begin = System.currentTimeMillis();
            long passedTime = 0;
            //没有结果
            while (response == null){

                long waitTime  = timeout - passedTime;
                //经历时间超过了最大等待时间
                if (waitTime <= 0){
                    break;
                }
                try {
                    this.wait(waitTime);//
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //求经历时间
                passedTime = System.currentTimeMillis() - begin;
            }
            return response;
        }
    }

    //create res
    public void  complete(Object response){
        synchronized (this){
            //
            this.response = response;
            this.notifyAll();
        }
    }
}

class Downloader{

    public static List<String> download() throws IOException {
        HttpURLConnection conn = (HttpURLConnection) new URL("https://www.baidu.com/").openConnection();
        List<String> lines = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(
                conn.getInputStream(), StandardCharsets.UTF_8
        ))){
            String line;
            while ((line = reader.readLine()) != null){
                lines.add(line);
            }
        }
        return lines;
    }
}