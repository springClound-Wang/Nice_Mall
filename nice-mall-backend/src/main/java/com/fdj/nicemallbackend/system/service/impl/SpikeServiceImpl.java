package com.fdj.nicemallbackend.system.service.impl;

import com.fdj.nicemallbackend.common.domain.SortConsts;
import com.fdj.nicemallbackend.common.domain.TypeConsts;
import com.fdj.nicemallbackend.common.domain.VerifyConsts;
import com.fdj.nicemallbackend.common.utils.RedisUtil;
import com.fdj.nicemallbackend.common.utils.TypeJudgeUtil;
import com.fdj.nicemallbackend.system.dto.GoodsDetail;
import com.fdj.nicemallbackend.system.dto.Result;
import com.fdj.nicemallbackend.system.entity.*;
import com.fdj.nicemallbackend.system.mapper.*;
import com.fdj.nicemallbackend.system.service.ISpikeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xns
 * @since 2019-10-03
 */
@Service
public class SpikeServiceImpl extends ServiceImpl<SpikeMapper, Spike> implements ISpikeService {

    @Autowired
    SpikeMapper spikeMapper;

    @Autowired
    TypeGoodsMapper typeGoodsMapper;

    @Autowired
    SortMapper sortMapper;

    @Autowired
    TypeClothesMapper typeClothesMapper;

    @Autowired
    TypeShoesMapper typeShoesMapper;

    @Autowired
    TypeElectronicMapper typeElectronicMapper;

    @Autowired
    TypePackageMapper typePackageMapper;

    @Autowired
    GoodsMapper goodsMapper;

    @Autowired
    StoreGoodsMapper storeGoodsMapper;

    @Autowired
    BusinessMapper businessMapper;

    @Autowired
    SortListNameMapper sortListNameMapper;

    @Autowired
    SortListTypeMapper sortListTypeMapper;

    /**
     * 添加秒杀商品,默认为当天内的秒杀
     * @param map
     * @return
     */
    @Transactional
    @Override
    public Result addSpikes(Map<String, Object> map) {
        List<Long> crazyGoodsId = (List<Long>)map.get("goodsCrazyList");
        RedisUtil redisUtil = new RedisUtil();
        for(int i=0;i<crazyGoodsId.size();i++){
            Goods goods = goodsMapper.selectAllById(crazyGoodsId.get(i));
            StoreGoods storeGoods = storeGoodsMapper.selectBygoodsId(crazyGoodsId.get(i));
            Business business = businessMapper.selectByBussinessId(storeGoods.getBusinessId());
            TypeGoods typeGoods = typeGoodsMapper.selectByGoodsId(crazyGoodsId.get(i));
            Sort sort = sortMapper.selectBySortId(typeGoods.getSortId());
            LocalDateTime start = LocalDateTime.now();
            Duration duration = Duration.between(start,(LocalDateTime) map.get("endTime"));
            if(TypeConsts.TYPE_CLOTHES.equals(sort.getSortEnglishName())){
                TypeClothes typeClothes = typeClothesMapper.selectByGoodsId(crazyGoodsId.get(i));
                SortListName sortListName = sortListNameMapper.selectById(typeGoods.getSortListNameId());
                SortListType sortListType = sortListTypeMapper.selectById(typeGoods.getSortListTypeId());
                List<String> imageDetail = Arrays.asList(typeClothes.getImageDetail().split(","));
                List<String> imageShow = Arrays.asList(typeClothes.getImageShow().split(","));
                List<String> color = Arrays.asList(typeClothes.getClothesColor().split(","));
                List<String> size = Arrays.asList(typeClothes.getClothesSize().split(","));
                GoodsDetail<TypeClothes> goodsAll = new GoodsDetail<>(goods,business.getStoreName(),"clothes", TypeJudgeUtil.judgeType(sortListName,sortListType),imageDetail,imageShow,color,size,typeClothes,(LocalDateTime)map.get("endTime"));
                redisUtil.set(VerifyConsts.SPIKE_CACHE_PREFIX+crazyGoodsId.get(i),goodsAll,duration.toMinutes()*60);
            }else if(TypeConsts.TYPE_SHOES.equals(sort.getSortEnglishName())){
                TypeShoes typeShoes = typeShoesMapper.selectByGoodsId(crazyGoodsId.get(i));
                List<String> imageDetail = Arrays.asList(typeShoes.getImageDetail().split(","));
                List<String> imageShow = Arrays.asList(typeShoes.getImageShow().split(","));
                List<String> color = Arrays.asList(typeShoes.getShoesColor().split(","));
                List<String> size = Arrays.asList(typeShoes.getShoesSize().split(","));
                GoodsDetail<TypeShoes> goodsAll = new GoodsDetail<>(goods,business.getStoreName(),"shoes","shoes" ,imageDetail,imageShow,color,size,typeShoes,(LocalDateTime)map.get("endTime"));
                redisUtil.set(VerifyConsts.SPIKE_CACHE_PREFIX+crazyGoodsId.get(i),goodsAll,duration.toMinutes()*60);
            }else if(TypeConsts.TYPE_PACKAGE.equals(sort.getSortEnglishName())){
                TypePackage typePackage = typePackageMapper.selectByGoodsId(crazyGoodsId.get(i));
                List<String> imageDetail = Arrays.asList(typePackage.getImageDetail().split(","));
                List<String> imageShow = Arrays.asList(typePackage.getImageShow().split(","));
                List<String> color = Arrays.asList(typePackage.getPackageColor().split(","));
                List<String> size = Arrays.asList(typePackage.getPackageSize().split(","));
                GoodsDetail<TypePackage> goodsAll = new GoodsDetail<>(goods,business.getStoreName(),"package","package" ,imageDetail,imageShow,color,size,typePackage,(LocalDateTime)map.get("endTime"));
                redisUtil.set(VerifyConsts.SPIKE_CACHE_PREFIX+crazyGoodsId.get(i),goodsAll,duration.toMinutes()*60);
            }else if(TypeConsts.TYPE_ELECTRONIC.equals(sort.getSortEnglishName())){
                TypeElectronic typeElectronic = typeElectronicMapper.selectByGoodsId(crazyGoodsId.get(i));
                List<String> imageDetail = Arrays.asList(typeElectronic.getImageDetail().split(","));
                List<String> imageShow = Arrays.asList(typeElectronic.getImageShow().split(","));
                List<String> color = Arrays.asList(typeElectronic.getElectronicColor().split(","));
                List<String> size = Arrays.asList(typeElectronic.getElectronicFormat().split(","));
                GoodsDetail<TypeElectronic> goodsAll = new GoodsDetail<>(goods,business.getStoreName(),"shoes","shoes" ,imageDetail,imageShow,color,size,typeElectronic,(LocalDateTime)map.get("endTime"));
                redisUtil.set(VerifyConsts.SPIKE_CACHE_PREFIX+crazyGoodsId.get(i),goodsAll,duration.toMinutes()*60);
            }
            Spike spike = new Spike(crazyGoodsId.get(i),sort.getSortId(),storeGoods.getBusinessId(),goods.getGoodsPrePrice(),BigDecimal.valueOf((Long)map.get("goodsCurPrice")),(Long)map.get("storeGoodsNumber"),(Long)map.get("storeGoodsNUmber"),(LocalDateTime)map.get("startTime"),(LocalDateTime)map.get("endTime"));
            spikeMapper.save(spike);
        }
        return new Result().success("添加成功!!!");
    }
}
