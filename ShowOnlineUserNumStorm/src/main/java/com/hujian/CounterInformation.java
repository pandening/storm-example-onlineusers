package com.hujian;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/7/4.
 * this class is signleton
 */
public class CounterInformation {
    //the TAG of this class
    private final String TAG="COUNTER-INFORMATION";
    //the signleton instance(only one)
    private static CounterInformation _instance_=null;
    //the username counter
    Map<String,Integer> UserNameCounter;
    //the ip counter
    Map<String,Integer> IpCounter;
    //log action counter
    Map<String,Integer> LogActionCounter;
    //takes time counter
    Map<String,Integer> TakesTimeCounter;
    //the date counter
    Map<String,Integer> ActionDateCounter;
    //platform counter
    Map<String,Integer> PlatformCounter;

    //the constructor,it's private,so you can not use this constructor
    private CounterInformation(){
        UserNameCounter=new HashMap<String, Integer>();
        IpCounter=new HashMap<String, Integer>();
        LogActionCounter=new HashMap<String, Integer>();
        TakesTimeCounter=new HashMap<String, Integer>();
        ActionDateCounter=new HashMap<String, Integer>();
        PlatformCounter=new HashMap<String, Integer>();
    }

    //you can only use this function to get the instance of this class
    public static CounterInformation getInstance(){
        if(_instance_!=null){
            return _instance_;
        }else{
            MyLog.log("Only constructor done for the signleton instance of CounterInformation!");
            _instance_=new CounterInformation();
            return _instance_;
        }
    }
}
