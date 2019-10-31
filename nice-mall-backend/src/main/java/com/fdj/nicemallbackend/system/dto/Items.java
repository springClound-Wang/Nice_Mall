package com.fdj.nicemallbackend.system.dto;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * @Classname Qgoods
 * @Description TODO
 * @Date 19-10-28 下午8:49
 * @Created by xns
 */
@Data
@Setting(settingPath = "/settings/elasticsearch_setting.json")
@Document(indexName="items",type = "docs",shards = 1,replicas = 0)
public class Items {
    /**
     * goodsId
     */
    @Id
    private Long id;
    /**
     * 所有需要被搜索的信息，包含标题，分类，描述,
     */
    @Field(type = FieldType.Text,analyzer = "ik_max_word_pinyin")
    private String all;
    /**
     * 品牌
     */
    @Field(type = FieldType.Keyword)
    private String brand;
    /**
     * 1级分类id
     */
    private Integer sid1;
    /**
     * 2级分类id
     */
    private Integer sid2;
    /**
     * 3级分类id
     */
    private Integer sid3;
    /**
     * 价格
     */
    private BigDecimal price;
    /**
     * List<Findgoods>的json结构
     */
    @Field(type = FieldType.Keyword,index=false)
    private String findGoods;
}
