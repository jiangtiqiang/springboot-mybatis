package com.wonders.javaeightdemo;

import java.text.Collator;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author huangwieyue
 * @date 2018-02-17 17:08
 * Lambda表达式
 * 编程中提到的 lambda 表达式，通常是在需要一个函数，但是又不想费神去命名一个函数的场合下使用，也就是指匿名函数
 * 详见：http://www.importnew.com/16436.html
 */
public class LambdaDemo {
    public static void main(String[] args) {
        List<String> list= Arrays.asList("8","4","6","5");
        Collections.sort(list,((o1, o2) -> Collator.getInstance().compare(o1,o2)));
        System.out.println(list);
    }
}
