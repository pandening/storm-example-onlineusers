package com.hujian;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;

import java.util.Map;

/**
 * Created by Administrator on 2016/7/4.
 */
public class CounterNormalizerBolt extends BaseRichBolt {
    //the TAG of this class
    private final String TAG="COUNTER-NORMALIZER-BOLT";
    //the emit desc array
    private final String[] DescArray={"logdate","logstatus","logusername","logip","logtime","logplatform"};
    //teh collector
    private OutputCollector _collector;

    public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
        //get the collector
        this._collector=outputCollector;
    }

    public void execute(Tuple tuple) {
        //i will get the tuple from the spout,then i will send each element of
        //the tuple such as username,ip and so on
        //and the bolts that recvicve the tuple will count it
        //the format that i send such like this=>
        //('logdate','...')
        //('logstatus','...')
        //('logusername','..')
        //('logip','..')
        //('logtime','..')
        //('logplatform','..')
        int index=0;
        for(String item:tuple.getString(0).split("#")){
            this._collector.emit(new Values(DescArray[index++],item.toString()));
            MyLog.log(this.TAG,"Emit an item's Element ok!");
        }
    }
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        //i will send the tuple like this..
        //('username','hujian')
        //('ip','10.12.10.2')
        //...
        outputFieldsDeclarer.declare(new Fields("Desc","Value"));
    }
}
