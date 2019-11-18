package com.fdj.nicemallbackend;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fdj.nicemallbackend.system.dto.Findgoods;
import com.fdj.nicemallbackend.system.dto.Items;
import com.fdj.nicemallbackend.system.dto.Result;
import com.fdj.nicemallbackend.system.service.IMixService;
import com.fdj.nicemallbackend.system.service.ISearchService;
import com.fdj.nicemallbackend.system.service.ISpikeService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Classname HomePageTest
 * @Description TODO
 * @Date 19-10-9 下午9:36
 * @Created by xns
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class HomePageTest {
    @Resource
    IMixService iMixService;

    @Resource
    ISearchService iSearchService;

    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Test
    public void getHomePage_test(){
        Result result= iMixService.getHomePage();
        System.out.println(((Map<String,Object>)result.getData()).get("typeEntry"));
    }

    @Test
    public void es_test(){
        List<Items> items = iSearchService.search("孕妇装");
        List<Findgoods> findgoods = new ArrayList<>();
        items.forEach(items1 -> {
            Findgoods findgoods1 = null;
            try {
                findgoods1 = MAPPER.readValue(items1.getFindGoods(), Findgoods.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            System.out.println(findgoods1);
        });

    }
}
