package com.hujian;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichSpout;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;
import backtype.storm.utils.Utils;

import java.util.Map;

/**
 * Created by Administrator on 2016/7/4.
 */
public class LogItemProducerSpout extends BaseRichSpout {
    //this is the log flag
    private final String TAG="LOG_ITEM_PRODUCER_SPOUT";
    //this is the collector
    private SpoutOutputCollector _collector;
    //the Random object
    RandomUtilsClass RandomOBJ;//=new RandomUtilsClass();


    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        //i will emit the value named "LOG_ITEM"
        outputFieldsDeclarer.declare(new Fields("LOG_ITEM"));
    }

    public void open(Map map, TopologyContext topologyContext, SpoutOutputCollector spoutOutputCollector) {
        //GET THE COLLECTOR
        this._collector=spoutOutputCollector;
        //get the random object
        RandomOBJ=new RandomUtilsClass();
    }

    //i will get the tuple and emit the tuple "LOG_ITEM"
    public void nextTuple() {
        //every 1 second,send an item..
        Utils.sleep(10);
        //emit the log item
        _collector.emit(new Values(RandomOBJ.getInstanceofLogItemStructure().toString()));
        //log
        MyLog.log(this.TAG,"Emit a log item ok...");
    }
}
