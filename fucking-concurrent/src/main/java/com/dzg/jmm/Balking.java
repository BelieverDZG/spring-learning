package com.dzg.jmm;

/**
 * 犹豫模式:用在一个线程发现另一个线程或本线程已经做了某一件相同的事情，那么本线程就无需在做了
 * 直接返回
 */
public class Balking {

    public static void main(String[] args) {
        TwoPhaseTermination tpt = new TwoPhaseTermination();
        tpt.start();
        tpt.start();
    }
}

class TwoPhaseTermination{

    private Thread monitor;

    private volatile boolean stop = false;

    private boolean started = false;
    public void start(){
        synchronized (this){
            if (started){
                return;
            }
            started = true;
        }
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
