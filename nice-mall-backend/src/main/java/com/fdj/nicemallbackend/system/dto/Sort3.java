package com.fdj.nicemallbackend.system.dto;

import lombok.Data;

import java.util.List;

/**
 * @Classname Sort3
 * @Description TODO
 * @Date 19-9-3 下午9:20
 * @Created by xns
 */
@Data
public class Sort3 {
    /**
     * 二阶分类名
     */
    String goodsListName;
    /**
     * 所有三阶分类名
     */
    List<String> goodsAll;

    public Sort3(String sortListName, List<String> goodsAll) {
        this.goodsListName = sortListName;
        this.goodsAll=goodsAll;
    }
}
