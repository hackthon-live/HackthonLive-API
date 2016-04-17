package com.iydsj.sw.common.utils;

import java.text.DecimalFormat;

/**
 * 金额工具类
 *
 * @author yanyan.wang
 * @date 2016-01-06 11:18
 */
public class MoneyUtils {


    private final static int RATE = 1000;

    public static long getValue(String value) {
        return getValue(Double.parseDouble(value));
    }

    public static long getValue(float value) {
        return Long.parseLong(Math.round(value * RATE) + "");
    }

    public static long getValue(double value) {
        return Long.parseLong(Math.round(value * RATE) + "");
    }


    /**
     * 显示值
     *
     * @param value 值
     * @return
     */
    public static String displayValue(long value) {
        return Math.round(value / RATE) + "";
    }

    /**
     * 获取显示值
     *
     * @param value  值
     * @param format 显示格式,如#.00
     * @return
     */
    public static String displayValue(long value, String format) {
        DecimalFormat decimalFormat = new DecimalFormat(format);
        return decimalFormat.format(value * 1d / RATE);
    }

    public static void main(String[] args){
        long value=getValue("123.5");
        System.out.println(value);
    }

}
