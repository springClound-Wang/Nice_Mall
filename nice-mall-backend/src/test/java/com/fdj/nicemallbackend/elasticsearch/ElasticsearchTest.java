package com.fdj.nicemallbackend.elasticsearch;

import com.fdj.nicemallbackend.system.dto.Items;
import com.fdj.nicemallbackend.system.entity.Goods;
import com.fdj.nicemallbackend.system.mapper.GoodsMapper;
import com.fdj.nicemallbackend.system.repository.ItemRepository;
import com.fdj.nicemallbackend.system.service.ISearchService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Classname test
 * @Description TODO
 * @Date 19-10-30 下午8:41
 * @Created by xns
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ElasticsearchTest {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ISearchService iSearchService;

    @Autowired
    private GoodsMapper goodsMapper;


    @Test
    public void testcreate(){
        this.elasticsearchTemplate.createIndex(Items.class);
        this.elasticsearchTemplate.putMapping(Items.class);
    }

    @Test
    public void test(){
        //获取所有的商品
        List<Goods> goods = this.goodsMapper.select();
        //处理List<Goods> ==> List<Item>
        List<Items> itemList = goods.stream().map(good -> {
            try {
                return this.iSearchService.buildGoods(good);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }).collect(Collectors.toList());

        //执行新增数据
        this.itemRepository.saveAll(itemList);
    }
    //rm data/.lock
    //./bin/logstash -f config/mysql.conf &
}
