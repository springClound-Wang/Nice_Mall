package com.fdj.nicemallbackend;

import com.fdj.nicemallbackend.system.dto.Result;
import com.fdj.nicemallbackend.system.dto.Spikes;
import com.fdj.nicemallbackend.system.entity.Spike;
import com.fdj.nicemallbackend.system.service.ISpikeService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import javax.annotation.Resource;

/**
 * @Classname SpikeServiceTest
 * @Description TODO
 * @Date 19-10-7 上午11:22
 * @Created by xns
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpikeServiceTest {
    @Resource
    ISpikeService iSpikeService;


    @Test
    public void getPartNews_test(){
        Result result = iSpikeService.getPartNews(Long.valueOf("3"));
        System.out.println(((Spikes)result.getData()).getStoreGoodsNumber()+"&&%**@%$#&%&$#%"+((Spikes)result.getData()).getStartTime());
    }
}
