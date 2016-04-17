package com.iydsj.sw.common.utils;

import com.iydsj.sw.common.utils.exceptions.BizException;

/**
 * 时间工具类
 *
 * @author yanyan.wang
 * @date 2016-01-31 21:35
 */
public class TimeUtils {

    /**
     * 支持时分转换成秒
     *
     * @param time
     * @return
     */
    public static long convertToSec(String time) {
        String[] times = time.split(":");
        if (times.length < 2) {
            throw new BizException("时分转为毫秒错误");
        }
        int hours = Integer.parseInt(times[0]);
        int minutes = Integer.parseInt(times[1]);
        int seconds = 0;
        if (times.length == 3) {
            seconds = Integer.parseInt(times[2]);
        }
        return hours * 3600 + minutes * 60 + seconds;
    }

    /**
     * 支持毫秒转换成时分 HH:mm
     *
     * @param sec
     * @return
     */
    public static String convertToTime(long sec) {
        return String.format("%02d", sec / 3600) + ":" + String.format("%02d", sec % 3600 / 60);
    }
}
