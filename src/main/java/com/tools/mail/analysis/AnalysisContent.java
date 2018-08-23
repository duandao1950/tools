package com.tools.mail.analysis;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Created by zhoushu on 2018/3/14 0014.
 */
public class AnalysisContent {

    public static void main(String[] args) throws IOException {
        List<Path> paths = Files.list(Paths.get("F:\\adminmail1\\")).collect(Collectors.toList());
        /**
         * 分析日志中所有的异常信息
         */
        List<String> exceptions = Lists.newArrayList();
        for (Path path:paths){
            List<String> contents = Files.readAllLines(path);
            for(String content:contents){
                String matchString = subString(content);
                if(!Strings.isNullOrEmpty(matchString)){
                    exceptions.add(matchString);
                }
            }
        }

        /**
         * 分组统计
         */
        Map<String,Long> groupCountMap = exceptions.stream().collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
        Map<String,Long> sortMap = new LinkedHashMap<>();
        /**
         * 分组统计后的结果排序
         */
        groupCountMap.entrySet().stream().sorted(Map.Entry.<String,Long>comparingByValue().reversed())
                .forEachOrdered(t -> sortMap.put(t.getKey(), t.getValue()));
        for(String key:sortMap.keySet()){
            System.out.println(key+":"+sortMap.get(key));
        }
        System.out.println("总数:"+groupCountMap.size());
    }

    public static String subString(String content){
        //String regex = "(\\.)(?!.*\\1)(.*)Exception";
        //String regex = "(\\.)(?!.*\\1)(\\w+)Exception";
        String regex = "((?<=\\.)|(?<=\\[))(\\w+)Exception";
        Pattern pattern = Pattern.compile(regex);
        Matcher m = pattern.matcher(content);
        while (m.find()){
            String ex = m.group();
            return ex;
        }
        return "";
    }
}
