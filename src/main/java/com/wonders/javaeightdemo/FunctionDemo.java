package com.wonders.javaeightdemo;

import java.util.function.Function;

/**
 * @author huangwieyue
 * @date 2018-02-17 18:04
 * 基本使用 接收一个参数，返回一个结果 比如Function<String,Boolean>接收String 返回boolean
 * andThen
 * conmpose
 * andThen和conmpose一样 就是调用顺序不一样
 */
public class FunctionDemo {
    public static void main(String[] args) {
        Function<String,Boolean> f1=s1 -> s1 !=null;//默认Function基本使用
        Function<Boolean,Integer> f2=b -> b ? 1 : 0;



        Function<String,Integer> stringIntegerFunction1=f1.andThen(f2);//andThen协作
        /**
         * 这里注意 f1作为f2的入参，那么f1的返回值结果就应该和f2的入参Boolean类型保持一致
         */
        Function<String,Integer> stringIntegerFunction2= f2.compose(f1);//compose协作

        /**
         * andThen()方法
         * 参数不为null，把f1结果传递给f2的第一个参数值作为入参，然后返回f2的判断结构
         */
        System.out.println(stringIntegerFunction1.apply("abc"));//传null试试

        /**
         *compose()方法
         */
        System.out.println(stringIntegerFunction1.apply(null));//传null试试
    }
}
