package com.wonders.javaeightdemo;

import java.util.function.Predicate;

/**
 * @author huangwieyue
 * @date 2018-02-17 17:48
 * Predicate and or negete
 * test()方法调用
 */
public class PredicateDemo {
    public static void main(String[] args) {
        Predicate<Integer> p1=age -> age>18;
        boolean result=p1.test(20);

        Predicate<Integer> p2=age ->age<30;

        Predicate<Integer> p1Andp2=p1.and(p2);


        System.out.println( p1Andp2.test(33));

    }
}
