package com.fdj.nicemallbackend.system.dto;

import com.fdj.nicemallbackend.system.entity.Goods;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Classname GoodsDetail
 * @Description TODO
 * @Date 19-10-4 下午2:48
 * @Created by xns
 */
@Data
public class GoodsDetail<T> {
    Goods goodsMain;

    String storeGoods;

    String goodsType;

    String goodsFlag;

    List<String> imageDetail;

    List<String> imageShow;

    List<String> color;

    List<String> size;

    T goodsDetail;

    LocalDateTime endTime;

    public GoodsDetail(Goods goodsMain,String storeGoods,String goodsType, String goodsFlag, List<String> imageDetail, List<String> imageShow, List<String> color, List<String> size, T goodsDetail, LocalDateTime endTime){
        this.goodsMain=goodsMain;
        this.storeGoods=storeGoods;
        this.goodsType=goodsType;
        this.goodsFlag = goodsFlag;
        this.imageDetail=imageDetail;
        this.imageShow=imageShow;
        this.color=color;
        this.size=size;
        this.goodsDetail=goodsDetail;
        this.endTime=endTime;
    }

}
