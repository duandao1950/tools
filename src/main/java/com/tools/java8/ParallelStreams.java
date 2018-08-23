package com.tools.java8;

import java.util.function.Function;
import java.util.stream.Stream;

/**
 * Created by zhoushu on 2018/3/16 0016.
 * 并行的不正确使用
 * iterate生成的是装箱的对象，必须拆箱成数字才能求和；
 * 我们很难把iterate分成多个独立块来并行执行。
 * 对于较小的数据量，选择并行流几乎从来都不是一个好的决定。并行处理少数几个元素 的好处还抵不上并行化造成的额外开销。
 * ArrayList的拆分效率比LinkedList 高得多，因为前者用不着遍历就可以平均拆分，而后者则必须遍历
 */
public class ParallelStreams {

    public static void main(String[] args) {
        System.out.println("Iterative sum done in:" +     measureSumPerf(ParallelStreams::iterativeSum, 10_000_000) + " msecs");

        System.out.println("Parallel sum done in: " +     measureSumPerf(ParallelStreams::parallelSum, 10_000_000) + " msecs" );
    }

    public static long iterativeSum(long n) {
        long result = 0;
        for (long i = 1L; i <= n; i++) {
            result += i;
        }
        return result;
    }

    public static long parallelSum(long n) {
        return Stream.iterate(1L, i -> i + 1).limit(n).parallel().reduce(0L, Long::sum);
    }

    public static long measureSumPerf(Function<Long, Long> adder, long n) {
        long fastest = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            long sum = adder.apply(n);
            long duration = (System.nanoTime() - start) / 1_000_000;
            //System.out.println("Result: " + sum);
            if (duration < fastest) {
                fastest = duration;
            }
        }
        return fastest;
    }
}
