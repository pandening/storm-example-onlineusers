package com.hujian;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Tuple;

import java.util.Map;

/**
 * Created by Administrator on 2016/7/4.
 */
public class CounterBolt extends BaseRichBolt {
    //this is the TAG
    private final String TAG="COUNTER-BOLT";
    //the collector
    private OutputCollector _collector;
    //the signleton counter information handler
    private CounterInformation Counter;
    //the emit desc array
    private final String[] DescArray={"logdate","logstatus","logusername","logip","logtime","logplatform"};

    public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
        //get the collector
        this._collector=outputCollector;
        //get the counter
        Counter=CounterInformation.getInstance();
    }

    public void execute(Tuple tuple) {
        //i will receve the tuple from the CounterNormalizerBolt
        //then i will update the counter information
        //and the information of counter is in-memory(you can flush the information to disk)
        //i can ensure the tuple has the format like this=>
        //(desc,value)
        //so,i will update the counter information according to the desc
        int index=0;
        for(;index<6&&!tuple.getString(0).equals(DescArray[index]);index++);
        switch(index)
        {
            case 0://date
            {
                MyLog.log(this.TAG,"Recv Date update information....");
                if(!Counter.ActionDateCounter.containsKey(tuple.getString(1))){
                    Counter.ActionDateCounter.put(tuple.getString(1),1);
                }else{
                    Counter.ActionDateCounter.put(tuple.getString(1),
                            Counter.ActionDateCounter.get(tuple.getString(1))+1);
                }
                break;
            }
            case 1://status
            {
                MyLog.log(this.TAG,"Recv status update information....");
                if(!Counter.LogActionCounter.containsKey(tuple.getString(1))){
                    Counter.LogActionCounter.put(tuple.getString(1), 1);
                }else{
                    Counter.LogActionCounter.put(tuple.getString(1),
                            Counter.LogActionCounter.get(tuple.getString(1))+1);
                }
                break;
            }
            case 2://username
            {
                MyLog.log(this.TAG,"Recv username update information....");
                if(!Counter.UserNameCounter.containsKey(tuple.getString(1))){
                    Counter.UserNameCounter.put(tuple.getString(1),1);
                }else{
                    Counter.UserNameCounter.put(tuple.getString(1),
                            Counter.UserNameCounter.get(tuple.getString(1))+1);
                }
                break;
            }
            case 3://ip
            {
                MyLog.log(this.TAG,"Recv ip update information....");
                if(!Counter.IpCounter.containsKey(tuple.getString(1))){
                    Counter.IpCounter.put(tuple.getString(1),1);
                }else{
                    Counter.IpCounter.put(tuple.getString(1),
                            Counter.IpCounter.get(tuple.getString(1))+1);
                }
                break;
            }
            case 4://time
            {
                MyLog.log(this.TAG,"Recv taketime update information....");
                if(!Counter.TakesTimeCounter.containsKey(tuple.getString(1))){
                    Counter.TakesTimeCounter.put(tuple.getString(1),1);
                }else{
                    Counter.TakesTimeCounter.put(tuple.getString(1),
                            Counter.TakesTimeCounter.get(tuple.getString(1))+1);
                }
                break;
            }
            case 5://platform
            {
                MyLog.log(this.TAG,"Recv platform update information....");
                if(!Counter.PlatformCounter.containsKey(tuple.getString(1))){
                    Counter.PlatformCounter.put(tuple.getString(1),1);
                }else{
                    Counter.PlatformCounter.put(tuple.getString(1),
                            Counter.PlatformCounter.get(tuple.getString(1))+1);
                }
                break;
            }
            default://nothing
            {
                MyLog.log(this.TAG,"Recv Error Tuple When update the counter information");
                MyLog.log(this.TAG,"["+tuple.getString(0)+","+tuple.getString(1)+"]");
                break;
            }
        }
    }

    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        //no more bolt will recv the tuple from this bolt
        //so we can do nothing in this method.
    }
}
