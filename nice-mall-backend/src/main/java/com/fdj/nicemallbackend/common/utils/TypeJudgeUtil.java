package com.fdj.nicemallbackend.common.utils;

import com.fdj.nicemallbackend.system.entity.SortListName;
import com.fdj.nicemallbackend.system.entity.SortListType;
import com.fdj.nicemallbackend.system.mapper.SortListNameMapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Classname TypeJudgeUtil
 * @Description TODO
 * @Date 19-10-4 下午4:30
 * @Created by xns
 */
public class TypeJudgeUtil {

    public static String judgeType(SortListName sortListName,SortListType sortListType){
        if(sortListName.getSortListName().contains(("裤"))) {
            if (sortListType.getSortListName().contains("女")) {
                return "clothesDownWoman";
            }else if(sortListType.getSortListName().contains("男")){
                return "clothesDownMen";
            }
        }
        else{
            if(sortListType.getSortListName().contains("裙")){
                return "clothesSkirt";
            }
            else if(sortListName.getSortListName().contains("女")){
                return "clothesUpWoman";
            }
            else if(sortListName.getSortListName().contains("男")){
                return "clothesUpMen";
            }
            else{
                return "clothesUpWoman";
            }
        }
        return "无匹配";
    }
}
