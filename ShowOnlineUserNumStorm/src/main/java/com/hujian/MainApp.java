package com.hujian;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.StormSubmitter;
import backtype.storm.generated.AlreadyAliveException;
import backtype.storm.generated.InvalidTopologyException;
import backtype.storm.topology.TopologyBuilder;
import backtype.storm.utils.Utils;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 2016/7/4.
 */
public class MainApp {
    private final String TAG="MAIN-APP";
    //the builder
    private static TopologyBuilder builder=new TopologyBuilder();

    public static void main(String[] args){
        MyLog.log("start to run main app");

        //ok,the follow codes will set up the storm
        Config config=new Config();
        //set the spout
        builder.setSpout("LogItemProducerSpout",new LogItemProducerSpout(),5000);
        //set the normalizerBolt
        builder.setBolt("CounterNormalizerBolt",new CounterNormalizerBolt(),10000)
                .shuffleGrouping("LogItemProducerSpout");
        //set the counter bolt
        builder.setBolt("CounterBolt",new CounterBolt(),20000)
                .shuffleGrouping("CounterNormalizerBolt");

        config.setDebug(false);

        //choose the run mode.
        if(args!=null&&args.length>0){
            try{
                config.setNumWorkers(1);
                StormSubmitter.submitTopology(args[0],config,builder.createTopology());
            } catch (InvalidTopologyException e) {
                e.printStackTrace();
            } catch (AlreadyAliveException e) {
                e.printStackTrace();
            }
        }else{
            config.setMaxTaskParallelism(1);
            LocalCluster cluster=new LocalCluster();
            cluster.submitTopology("Counter",config,builder.createTopology());

            MyLog.log("main:start to run storm cluster local!");

            /*
            * if you want to shut down the cluster after run some time,just use
            * the follow codes.
            * */
            //Utils.sleep(100000);
            //cluster.killTopology("Counter");
            //cluster.shutdown();

            //ok,i will set up the pig(print counter information)
            Timer timer=new Timer();
            timer.schedule(new PrintCounterInformationHandler(),1000,3000);
        }
    }
}
