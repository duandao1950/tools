package com.tools.mail;

import com.google.common.collect.Lists;
import org.springframework.lang.NonNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by zhoushu on 2018/3/14 0014.
 */
public class Test {
    public static void main(String[] args) throws IOException {
//        String content = "Hello World !!";
//        Files.write(Paths.get("F:\\adminmail1\\2779附件内容.txt"), content.getBytes());
        //String exception = "<b>Time:</b> 2018-03-08T21:21:29.092Z<br><b>Host:</b> jar-api_jar-api_2<br><b>Content:</b><br><br>com.netflix.hystrix.exception.HystrixRuntimeException.";
//        String exception = "<b>Time:</b> 2018-03-08T21:21:29.092Z<br><b>Host:</b> jar-api_jar-api_2<br><b>Content:</b><br><br>com.netflix.hystrix.exception.HystrixRuntimeException: StockCommandApi#queryFullStockStyleIds(GBOnlineStockQueryForm) failed and no fallback available.";
//        System.out.println(subHostString(exception));

//        List<Integer> intStream = Lists.newArrayList(1,2,3);
//        OptionalDouble s = intStream.stream().mapToInt(t -> t).average();
//        if(s.isPresent()){
//            System.out.println(s);
//        }
//
//        IntStream ints = IntStream.range(1,100).filter(t -> t % 2 == 0);
//        System.out.println(ints.count());
//
//        IntStream int1s = IntStream.rangeClosed(1,100).filter(t -> t % 2 == 0);
//        System.out.println(int1s.count());
//
//        List<Integer> integers = Stream.of(1,2,3,4,5,6).collect(Collectors.toList());
//        System.out.println(integers);
//
//        int[] numbers = {1,2,3,4,5,6};
//        int sum = Arrays.stream(numbers).sum();
//        System.out.println(sum);

        double d1 = 3.14; double d2 = d1;
        Double o1 = d1;
        Double o2 = d2;
        Double ox = o1;
        System.out.println(d1 == d2 ? "yes" : "no");
        System.out.println(Objects.equals(o1,o2) ? "yes" : "no");
        System.out.println(Objects.equals(o1,ox) ? "yes" : "no");

        String authors = String.join(", ", "1", "2", "3","4");
        System.out.println(authors);
    }

    public static String subHostString(String content){
        String regex = "Host(.*)Content";
        Pattern pattern = Pattern.compile(regex);
        Matcher m = pattern.matcher(content);
        while (m.find()){
            String ex = m.group();
            return ex;
        }
        return "";
    }

    public static String subExceptionString(String content){
        String regex = "((?<=\\.)|(?<=\\[))(\\w+)Exception";
        //String regex = "(\\.)(?!.*\\1)(\\w+)Exception";
        Pattern pattern = Pattern.compile(regex);
        Matcher m = pattern.matcher(content);
        while (m.find()){
            String ex = m.group();
            return ex;
        }
        return "";
    }
}
