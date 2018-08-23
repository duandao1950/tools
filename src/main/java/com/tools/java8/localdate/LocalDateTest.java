package com.tools.java8.localdate;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.chrono.JapaneseDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.TextStyle;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAdjusters;
import java.util.Locale;

/**
 * Created by zhoushu on 2018/4/3 0003.
 */
public class LocalDateTest {

    public static void main(String[] args) {
        LocalDate date = LocalDate.of(2014, 3, 18);
        int year = date.getYear();
        Month month = date.getMonth();
        int day = date.getDayOfMonth();
        DayOfWeek dow = date.getDayOfWeek();
        int len = date.lengthOfMonth();
        boolean leap = date.isLeapYear();
        System.out.println(dow.getDisplayName(TextStyle.FULL_STANDALONE,Locale.CHINA));
        LocalDate date1 = date.withMonth(7);
        System.out.println(date1);
        Period tenDays = Period.between(LocalDate.of(2014, 3, 8),
                LocalDate.of(2014, 3, 18));
        System.out.println(tenDays.getYears());

        LocalDate date2 = LocalDate.of(2014, 3, 18);
        date2 = date2.with(ChronoField.MONTH_OF_YEAR, 9);
        date2 = date2.plusYears(2).minusDays(10);
        System.out.println(date2);
        date2 = date2.withYear(2011);
        System.out.println(date2);

        LocalDate date3 = LocalDate.now();
        date3 = date3.with(TemporalAdjusters.nextOrSame(DayOfWeek.FRIDAY));
        System.out.println(date3);

        DateTimeFormatter italianFormatter = new DateTimeFormatterBuilder()
                .appendText(ChronoField.DAY_OF_MONTH)
                .appendLiteral(". ")
                .appendText(ChronoField.MONTH_OF_YEAR).appendLiteral(" ")
                .appendText(ChronoField.YEAR)
                .parseCaseInsensitive()
                .toFormatter(Locale.CHINA);
        System.out.println(italianFormatter);

        LocalDate date4 = LocalDate.of(2014, Month.MARCH, 18);
        JapaneseDate japaneseDate = JapaneseDate.from(date4);
        System.out.println(japaneseDate);
    }
}
