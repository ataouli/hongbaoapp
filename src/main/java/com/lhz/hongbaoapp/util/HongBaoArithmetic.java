package com.lhz.hongbaoapp.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by LHZ on 2017/1/12.
 */
public class HongBaoArithmetic {
    private HongBaoArithmetic(){}
    //最小红包额度
    private static final double MINMONEY = 0.01;
    //最大红包额度
    private static final double MAXMONEY = 200;

    private static boolean isRight(double money, int count) {
        double avg = money / count;
        if (avg < MINMONEY) {
            return false;
        }
        if (avg > MAXMONEY) {
            return false;
        }
        return true;
    }

    private static double random(double money, double minS, double maxS, int count) {
        //红包数量为1，直接返回金额
        if (count == 1) {
            return new BigDecimal(money).setScale(2, RoundingMode.HALF_UP).doubleValue();
        }
        //如果最大金额和最小金额相等，直接返回金额
        if (minS == maxS) {
            return new BigDecimal(minS).setScale(2, RoundingMode.HALF_UP).doubleValue();
        }
        double max = maxS > money ? money : maxS;
        //分配红包正确情况，允许红包的最大值
        double maxY = money - (count - 1) * minS;
        //分配红包正确情况，允许红包的最小值
        double minY = money - (count - 1) * maxS;
        //随机产生红包的最小值
        double min = minY > minS ? minY : minS;
        //随机产生红包最大值
        max = maxY < max ? maxY : max;
        //随机产生一个红包
        double value=Math.random() * (max - min) + min;
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    //每个红包最大是平均值的倍数
    private static final double TIMES = 2;

    public static List<Double> splitRedPackets(double money, int count) {
        if (!isRight(money, count)) {
            return null;
        }
        List<Double> list = new ArrayList<>();
        //红包最大金额为平均金额的TIMES倍
        double max = money * TIMES / count;
        max = max > MAXMONEY ? MAXMONEY : max;
        for (int i = 0; i < count; i++) {
            double one = random(money, MINMONEY, max, count - i);
            list.add(one);
            money -= one;
        }
        return list;
    }

    public static void main(String[] args) {
        List<Double> list=splitRedPackets(100,5);
        System.out.println(list);
    }
}
