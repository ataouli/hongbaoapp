package com.lhz.hongbaoapp.util;

import com.lhz.hongbaoapp.entity.HongBao;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by LHZ on 2017/1/12.
 */
public class HongBaoUtil {
    private static final ConcurrentHashMap<Integer,ConcurrentLinkedQueue<Double>> hongBaoMap=new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<Integer,Double> PunishMap=new ConcurrentHashMap<>();

    private HongBaoUtil(){}

    public static void setHongbao(HongBao hongbao){
        List<Double> list=HongBaoArithmetic.splitRedPackets(hongbao.getMoney(),hongbao.getAmount());
        ConcurrentLinkedQueue<Double> linkedQueue=new ConcurrentLinkedQueue<>();
        for(Double dob:list){
            linkedQueue.add(dob);
        }
        int id=hongbao.getId();
        hongBaoMap.put(id,linkedQueue);
        Collections.sort(list);
        int index=list.size()-hongbao.getPunishRanking();
        PunishMap.put(id,list.get(index));
    }

    public static Map<String,String> receiveHongbao(HongBao hongbao){
        int id=hongbao.getId();
        Double value=hongBaoMap.get(id).poll();
        Map<String,String> map=new HashMap<>();
        if(value!=null){
            if(PunishMap.get(id)==value){
                map.put("momey",value+"");
                map.put("flag",1+"");
            }else{
                map.put("momey",value+"");
                map.put("flag",0+"");
            }
        }else{
            hongBaoMap.remove(id);
            PunishMap.remove(id);
        }
        return map;
    }


    public static void main(String[] args) {
        HongBao hongBao=new HongBao();
        hongBao.setMoney(100.00f);
        hongBao.setAmount(5);
        hongBao.setRoomID("123");
        hongBao.setId(1);
        hongBao.setPunishRanking(1);
        setHongbao(hongBao);

        for(int i=0;i<5;i++){
            System.out.println(receiveHongbao(hongBao));
        }

    }
}