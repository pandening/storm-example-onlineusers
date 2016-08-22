package com.hujian;


import java.util.Map;
import java.util.TimerTask;

/**
 * Created by Administrator on 2016/7/4.
 * this class just print the counter infoemation loop.
 * and you call once,print the counter information once!
 */
public class PrintCounterInformationHandler extends TimerTask{
    private final String TAG="COUNTER-INFORMATION-SHOW";
    //the Counter object
    CounterInformation Counter;
    PrintCounterInformationHandler(){
        //get the instance of counter
        Counter=CounterInformation.getInstance();
    }

    @Override
    public void run() {
        this.Pig();
    }

    //this is the print counter api
    public void Pig()
    {
        MyLog.log(this.TAG,"---show the counter information----");
        MyLog.log("---------------------UserCounter Information-----------------------");
        for(Map.Entry<String,Integer> item:Counter.UserNameCounter.entrySet()){
            MyLog.log(item.getKey()+":"+item.getValue());
        }
        //MyLog.log("---------------------IP Counter Information-------------------------");
        // for(Map.Entry<String,Integer> item:Counter.IpCounter.entrySet()){
        //    MyLog.log(item.getKey()+":"+item.getValue());
        //}
        MyLog.log("----------------LogActionCounter Counter Information-------------------");
        for(Map.Entry<String,Integer> item:Counter.LogActionCounter.entrySet()){
            MyLog.log(item.getKey()+":"+item.getValue());
        }
        //MyLog.log("----------------TakesTimeCounter Counter Information-------------------");
        //for(Map.Entry<String,Integer> item:Counter.TakesTimeCounter.entrySet()) {
        //    MyLog.log(item.getKey() + ":" + item.getValue());
        //}
        //MyLog.log("----------------ActionDateCounter Counter Information-------------------");
        //for(Map.Entry<String,Integer> item:Counter.ActionDateCounter.entrySet()){
        //    MyLog.log(item.getKey()+":"+item.getValue());
        //}

        MyLog.log("----------------PlatformCounter Counter Information-------------------");
        for(Map.Entry<String,Integer> item:Counter.PlatformCounter.entrySet()){
            MyLog.log(item.getKey()+":"+item.getValue());
        }
    }
}
