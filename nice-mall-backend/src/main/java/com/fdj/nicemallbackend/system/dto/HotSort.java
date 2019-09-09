package com.fdj.nicemallbackend.system.dto;

import lombok.Data;

/**
 * @Classname HotSort
 * @Description TODO
 * @Date 19-9-9 下午8:31
 * @Created by xns
 */
@Data
public class HotSort {
    /**
     * 热门对应的小类型名称
     */
    private String typeName;

    /**
     * 热门相应的十张图片
     */
    private String imageDetails;

    public HotSort(String typename, String imageDetails) {

        this.typeName=typename;
        this.imageDetails = imageDetails;
    }
}
