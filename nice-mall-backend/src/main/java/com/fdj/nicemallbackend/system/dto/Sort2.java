package com.fdj.nicemallbackend.system.dto;

import lombok.Data;

import java.util.List;

/**
 * @Classname Sort2
 * @Description TODO
 * @Date 19-9-3 下午9:20
 * @Created by xns
 */
@Data
public class Sort2 {
    /**
     * 分类名称
     */
    String nameList;
    /**
     * 三阶类型数组
     */
    List<Sort3> goodsList;

    public Sort2(String sortName, List<Sort3> goodsList) {
        this.nameList = sortName;
        this.goodsList=goodsList;
    }
}
