package com.fdj.nicemallbackend;

import com.fdj.nicemallbackend.system.dto.Result;
import com.fdj.nicemallbackend.system.service.IMixService;
import com.fdj.nicemallbackend.system.service.ISpikeService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
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

    @Test
    public void getHomePage_test(){
        Result result= iMixService.getHomePage();
        System.out.println(((Map<String,Object>)result.getData()).get("spikeList"));
    }
}
