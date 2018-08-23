package com.tools.java8;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by zhoushu on 2018/3/16 0016.
 */
public class Test {
    public static void main(String[] args) {
        Stream.generate(Math::random).limit(5).forEach(System.out::println);

        int[] intArrays = IntStream.range(0,100).toArray();
        Arrays.stream(intArrays).sum();
        System.out.println(intArrays);
    }

    private void getIterateList(){
        Stream.iterate(new int[]{0, 1},t -> new int[]{t[1], t[0]+t[1]}).limit(20).forEach(t -> System.out.println("(" + t[0] + "," + t[1] +")"));
    }

    private void getStreamIterate(){
        Stream.iterate(0,t -> t + 2).limit(10).forEach(System.out::println);
    }
}
