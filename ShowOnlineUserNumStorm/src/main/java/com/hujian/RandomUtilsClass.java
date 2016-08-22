package com.hujian;

import java.util.Random;

/**
 * Created by Administrator on 2016/7/4.
 */
public class RandomUtilsClass {
    //this class just create an item of LogItemStructure by randomly
    private final String TAG="RANDOM-UTILS-CLASS";
    //this is the status array
    private String StatusArray[]={"LOG_IN","LOG_OUT","SIGN_IN"};
    //this is the platform array
    private String PlatFormArray[]={"WINDOWS","LINUX","MAC","IOS","ANDROID","OTHERS"};
    //the name list,in actual app,you need to access database to get the information
    private String UserNames[]={"Pandening","Ada","Hujian","Libai","Wanli","Fushang","TianRTen","Danio"};
    //this is the random object
    private Random random=new Random();

    public RandomUtilsClass(){}

    //the only api of this class
    public LogItemStructure getInstanceofLogItemStructure(){
        //date|status|username|ip|taketime|platform
        return new LogItemStructure(
                String.valueOf(System.currentTimeMillis()),
                this.StatusArray[(random.nextInt(this.StatusArray.length)+47)%this.StatusArray.length],
                this.UserNames[(random.nextInt(this.UserNames.length)+47)%this.UserNames.length],
                getIpAdr(),
                getTakeTime(10000),
                this.PlatFormArray[(random.nextInt(this.PlatFormArray.length)+47)%this.PlatFormArray.length]
        );
    }

    //get an random ip address
    private String getIpAdr(){
        return (random.nextInt(255))+"."+(random.nextInt(255))
                +"."+(random.nextInt(255))+"."+(random.nextInt(255));
    }

    //get an random String of int
    private String getTakeTime(int limit){
        return String.valueOf(random.nextInt(limit));
    }

}
