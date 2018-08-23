package com.tools.java8.completablefuture;

import com.google.common.base.Stopwatch;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * Created by zhoushu on 2018/4/2 0002.
 */
public class CompletableFutureTest {
    //Math.min(4, 100)
    private final Executor executor = Executors.newFixedThreadPool(100, new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r);
            t.setDaemon(true);
            return t;
        }
    });

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFutureTest test = new CompletableFutureTest();

        System.out.println("###异步执行开始###");
        Stopwatch stopwatch = Stopwatch.createStarted();
        List<CompletableFuture<Double>> prices = Lists.newArrayList();
        for(int i=0;i<100;i++) {
            CompletableFuture<Double> price = test.getPriceAsync1("future");
            prices.add(price);
        }

//        prices.stream().map(CompletableFuture::join).forEach(System.out::println);
        for(Future<Double> price:prices){
            System.out.println(price.get());
        }
        System.out.println(stopwatch.elapsed(TimeUnit.MILLISECONDS));

        System.out.println("###同步执行开始###");
        Stopwatch stopwatch1 = Stopwatch.createStarted();
        for(int i=0;i<10;i++) {
            System.out.println(test.calculatePrice("future"));
        }
        System.out.println(stopwatch1.elapsed(TimeUnit.MILLISECONDS));
    }

    public CompletableFuture<Double> getPriceAsync(String product) {
        CompletableFuture<Double> future = new CompletableFuture<>();
        new Thread(() -> {
            double price = calculatePrice(product);
            future.complete(price);
        }).start();
        return future;
    }

    public CompletableFuture<Double> getPriceAsync1(String product) {
        return CompletableFuture.supplyAsync(() -> calculatePrice(product),executor);
    }

    private double calculatePrice(String product) {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Random random = new Random();
        return random.doubles(1.0d,10.0d).findAny().getAsDouble();
    }
}
