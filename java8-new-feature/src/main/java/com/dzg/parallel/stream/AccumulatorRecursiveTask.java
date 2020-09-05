package com.dzg.parallel.stream;

import java.util.concurrent.RecursiveTask;

public class AccumulatorRecursiveTask extends RecursiveTask<Integer> {

    private final int start;
    private final int end;
    private final int[] data;
    private final int LIMIT = 3;

    public AccumulatorRecursiveTask(int start, int end, int[] data) {
        this.start = start;
        this.end = end;
        this.data = data;
    }

    /**
     * 将计算任务划分成A - B子集，长度小于等于三
     *
     * @return
     */
    @Override
    protected Integer compute() {

        if ((end - start) <= LIMIT) {
            int result = 0;
            for (int i = start; i < end; i++) {
                result += data[i];
            }
            return result;
        }
        int mid = (end + start) / 2;
        AccumulatorRecursiveTask left = new AccumulatorRecursiveTask(start, mid, data);
        AccumulatorRecursiveTask right = new AccumulatorRecursiveTask(mid, end, data);

        left.fork();

        Integer rightRes = right.compute();
        Integer leftRes = left.join();

        return rightRes + leftRes;
    }
}
