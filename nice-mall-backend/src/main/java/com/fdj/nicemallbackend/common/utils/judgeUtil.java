package com.fdj.nicemallbackend.common.utils;

import java.util.List;

/**
 * @Classname judgeUtil
 * @Description TODO
 * @Date 19-9-10 下午6:55
 * @Created by xns
 */
public class judgeUtil {

    /**
     * 判断多个字符串是否存在相等的,存在，返回true,否则返回false
     * equalsIgnoreCase(last) 如果参数不为null并且字符串相等，则此方法返回true，忽略大小写; 否则是假的。
     * @param lists
     * @return
     */
    public static boolean isEquals(List<String> lists){
        String last = null;
        for(int i=0;i<lists.size();i++){
            String str = lists.get(i);
            if(last!=null&&str.equalsIgnoreCase(last)){
                return true;
            }
            last=str;
        }
        return false;
    }
}
