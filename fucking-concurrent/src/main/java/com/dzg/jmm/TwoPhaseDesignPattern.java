package com.dzg.jmm;

public class TwoPhaseDesignPattern {
    public static void main(String[] args) throws InterruptedException {
        TwoPhase t = new TwoPhase();
        t.start();
        Thread.sleep(1000);
        t.stop();
    }
}

class TwoPhase{

    private Thread monitor;

    private volatile boolean stop = false;

    public void start(){
        monitor = new Thread(() -> {
            while(true){
                Thread thread = Thread.currentThread();
                if (stop){
                    System.out.println("料理后事");
                    break;
                }
                try {
                    Thread.sleep(1000);
                    System.out.println("执行监控记录");
                }catch (InterruptedException e){
                    e.printStackTrace();
                    thread.interrupt();
                }
            }
        });
        monitor.start();
    }

    public void stop(){
        monitor.interrupt();
        stop = true;
    }

}
