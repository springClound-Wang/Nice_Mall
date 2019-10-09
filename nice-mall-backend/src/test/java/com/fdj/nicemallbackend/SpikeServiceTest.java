package com.fdj.nicemallbackend;

import com.fdj.nicemallbackend.system.dto.Result;
import com.fdj.nicemallbackend.system.dto.Spikes;
import com.fdj.nicemallbackend.system.dto.goodsList;
import com.fdj.nicemallbackend.system.entity.Spike;
import com.fdj.nicemallbackend.system.service.IBusinessService;
import com.fdj.nicemallbackend.system.service.ISpikeService;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.asm.Advice;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

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

    @Resource
    IBusinessService iBusinessService;


    @Test
    public void getPartNews_test(){
        Result result = iSpikeService.getPartNews(Long.valueOf("3"));
        System.out.println(((Spikes)result.getData()).getStoreGoodsNumber()+"&&%**@%$#&%&$#%"+((Spikes)result.getData()).getStartTime());
    }

    @Test
    public void getgoodsList_test(){
        List<goodsList> lists= iBusinessService.getGoodsList(Long.valueOf("4"));
        for(int i=0;i<lists.size();i++){
            System.out.println(lists.get(i).isGoodsCrazy()+"****%&^%&^%&%^&%^&%&");
        }
    }

}
