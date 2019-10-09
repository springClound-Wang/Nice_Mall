package com.fdj.nicemallbackend.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * @Classname TimeUtil
 * @Description TODO
 * @Date 19-10-8 下午10:23
 * @Created by xns
 */
public class TimeUtil {

    /**
     * local时间转换成UTC时间
     * @param localTimes
     * @return
     */
    public static LocalDateTime localToUTC(LocalDateTime localTimes) {
        LocalDateTime localDateTime = localTimes.plusHours(8);
        return localDateTime;
    }
}
