package com.hujian;

/**
 * Created by Administrator on 2016/7/4.
 */
public class MyLog {
    public static void log(String tag,String msg){
        System.out.println("["+tag+"]::"+msg);
    }
    public static void log(String msg){
        System.out.println(msg);
    }
}
