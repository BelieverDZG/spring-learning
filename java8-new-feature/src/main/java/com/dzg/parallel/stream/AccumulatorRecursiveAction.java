package com.dzg.parallel.stream;

import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.atomic.AtomicInteger;

public class AccumulatorRecursiveAction extends RecursiveAction {

    private final int start;
    private final int end;
    private final int[] data;
    private final int LIMIT = 3;

    public AccumulatorRecursiveAction(int start, int end, int[] data) {
        this.start = start;
        this.end = end;
        this.data = data;
    }

    @Override
    protected void compute() {
        int res = 0;
        if ((end-start) <=LIMIT){
            for (int i = start; i < end; i++) {
//                res+=data[i];
                AccumulatorHelper.getAccumulate(data[i]);
            }
//            AccumulatorHelper.getAccumulate(res);
        }else{
            int mid = (start+end)/2;
            AccumulatorRecursiveAction leftAction = new AccumulatorRecursiveAction(start,mid,data);
            AccumulatorRecursiveAction rightAction = new AccumulatorRecursiveAction(mid,end,data);
            leftAction.fork();
            rightAction.fork();
            leftAction.join();
            rightAction.join();
        }

    }

    static class AccumulatorHelper {
        private final static AtomicInteger res = new AtomicInteger(0);

        static void getAccumulate(int value) {
            res.getAndAdd(value);
        }

        static int getResult() {
            return res.get();
        }

        static void reset() {
            res.set(0);
        }
    }
}
