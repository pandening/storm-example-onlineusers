package com.hujian;

import org.apache.commons.lang.enums.*;

/**
 * Created by Administrator on 2016/7/4.
 */
public class LogItemStructure {
    //the log TAG
    private final String TAG="LOG_ITEM_STRUCTURE";
    //this is a structure declarer
    private String logDate;  //time
    private String logStatus;  //login/logout/sign in
    private String logUserName; //user name
    private String logUserLogIP; //user ip
    private String logTimeTake; //the time the action takes
    private String logPlatform;  //windows/mac/linux/android/ios/others

    //you can also new object first,and then assign the item of this object
    //LogItemStructure(){}
    //also.you can assign the item of the object when you new it
    LogItemStructure(String...args){
        //ok,assert
        try{
            if(args.length!=6){
                throw  new Exception("args's length must be 6");
            }else{
                MyLog.log(this.TAG,"start to assign the structure item!");
                this.logDate=args[0];
                this.logStatus=args[1];
                this.logUserName=args[2];
                this.logUserLogIP=args[3];
                this.logTimeTake=args[4];
                this.logPlatform=args[5];
                //assert
                if(!AssertPlatform()){
                    throw new Exception("platform must be=>[windows][linux][mac][ios][android][others]");
                }
                if(!AssertStatus()){
                    throw new Exception("Status must be=>[log_in][log_out][sign_in]");
                }
            }
        }catch(Exception e){
            MyLog.log(this.TAG,"exit with code 1");
            System.exit(1);
        }
    }

    //test the log item
    //please call this function before using your object
    public void TestObject(){
        if(!AssertPlatform()||!AssertStatus()){
            MyLog.log(this.TAG,"--test failure,program will exit with code 1--");
            System.exit(1);
        }else{
            MyLog.log(this.TAG,"test succeed!");
        }
    }

    //set the date
    public void setLogDate(String date){
        this.logDate=date;
    }

    //set the status
    public void setLogStatus(String status){
        this.logStatus=status;
    }

    //set the user name
    public void setUserName(String username){
        this.logUserName=username;
    }

    //set the ip
    public void setLogIp(String ip){
        this.logUserLogIP=ip;
    }

    //set the time takes
    public void setLogTakeTime(String time){
        this.logTimeTake=time;
    }

    //set the time takes
    public void setLogTakeTime(Long time){
        this.logTimeTake=time.toString();
    }

    //set the platform
    public void setLogPlatform(String platform){
        this.logPlatform=platform;
    }

    //get the log time
    public String getLogTime(){
        return this.logDate;
    }

    //get the log status
    public String getLogStatus(){
        return this.logStatus;
    }

    //get the user name
    public String getLogUserName(){
        return this.logUserName;
    }

    //get the log ip
    public String getLogIP(){
        return this.logUserLogIP;
    }

    //get the time
    public String getTakeTime(){
        return this.logTimeTake;
    }

    //get the platform
    public String getLogPlatform(){
        return this.logPlatform;
    }

    //toString method
    public String toString(){
        return (this.logDate)+"#"+(this.logStatus)+"#"+(this.logUserName)+"#"+
                (this.logUserLogIP)+"#"+(this.logTimeTake)+"#"+(this.logPlatform);
    }

    //assert the status and platform
    private boolean AssertStatus(){
        return (this.logStatus==LogStructureEnum.LOG_IN.toString()||
        this.logStatus==LogStructureEnum.LOG_OUT.toString()||
        this.logStatus==LogStructureEnum.SIGN_IN.toString());
    }

    private boolean AssertPlatform(){
        //windows|linux|mac|ios|android|others
        return (this.logPlatform==LogStructureEnum.WINDOWS.toString()||
        this.logPlatform==LogStructureEnum.LINUX.toString()||
        this.logPlatform==LogStructureEnum.MAC.toString()||
        this.logPlatform==LogStructureEnum.IOS.toString()||
        this.logPlatform==LogStructureEnum.ANDROID.toString()||
        this.logPlatform==LogStructureEnum.OTHERS.toString());
    }
}
