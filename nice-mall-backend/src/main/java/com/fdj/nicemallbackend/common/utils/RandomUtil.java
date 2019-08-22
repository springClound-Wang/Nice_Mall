package com.fdj.nicemallbackend.common.utils;

import java.util.*;

/**
 * @Classname Random
 * @Description TODO
 * @Date 19-8-15 下午5:36
 * @Created by xns
 */
public class RandomUtil {

    private static Random rand;
    static{
        rand = new Random();
    }

    /**
     * 获取min~max之间一个随机数字
     * @param min
     * @param max
     * @return
     */
    public static int getRandomInt(int min,int max){
        return rand.nextInt(max-min+1)+min;
    }

    /**
     * 获取0~9之间任意一个值
     * @return
     */
    public static int getNum(){
        return getRandomInt(0,9);
    }

    /**
     * 随机获取一个字母
     */
    public static char getLetter(){
        char c=(char)getRandomInt(97,122);
        return c;
    }


    /**
     * @Description Generate random verification code
     * @return java.lang.String
     *
     **/
    public static String achieveCode() {  //由于数字 1 、 0 和字母 O 、l 有时分不清楚，所以，没有数字 1 、 0
        String[] beforeShuffle= new String[] {"0","1","2", "3", "4", "5", "6", "7", "8", "9"};
        /**
         * 将数组转换为集合
         */
        List list = Arrays.asList(beforeShuffle);
        /**
         * 打乱集合顺序
         */
        Collections.shuffle(list);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            /**
             * 将集合转化为字符串
             */
            sb.append(list.get(i));
        }
        /**
         * 截取字符串第4到8
         */
        return sb.toString().substring(3, 9);
    }

    /**
     * 生成一个默认的用户名
     */
    public static String achName() {
        List list = new ArrayList();
       StringBuffer sb = new StringBuffer();
       for(int i=1;i<=6;i++){
           if(i<=3){
               list.add(getLetter());
           }
           else{
               list.add(getNum());
           }
       }
       Collections.shuffle(list);
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
        }
       String userName = sb.toString();
       return userName;
    }

    /**
     * 让其唯一
     * @param havenUsers
     * @return
     */
    public static String achieveName(List<String> havenUsers){
        String userName = achName();
        if(havenUsers != null) {
            while (havenUsers.contains(userName)) {
                userName = achName();
            }
        }
        return userName;
    }
}
