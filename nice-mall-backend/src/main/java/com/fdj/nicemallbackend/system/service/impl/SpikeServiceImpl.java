package com.fdj.nicemallbackend.system.service.impl;

import com.fdj.nicemallbackend.common.domain.TypeConsts;
import com.fdj.nicemallbackend.common.domain.VerifyConsts;
import com.fdj.nicemallbackend.common.utils.RedisUtil;
import com.fdj.nicemallbackend.common.utils.TimeUtil;
import com.fdj.nicemallbackend.common.utils.TypeJudgeUtil;
import com.fdj.nicemallbackend.system.dto.GoodsDetail;
import com.fdj.nicemallbackend.system.dto.Result;
import com.fdj.nicemallbackend.system.dto.Spikes;
import com.fdj.nicemallbackend.system.entity.*;
import com.fdj.nicemallbackend.system.mapper.*;
import com.fdj.nicemallbackend.system.service.ISpikeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

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

    @Autowired
    RedisUtil redisUtil;


    /**
     * 添加秒杀商品,默认为当天内的秒杀
     * @param lists
     * @return
     */
    @Transactional
    @Override
    public Result addSpikes(List <Spikes> lists) {
        for(int i=0;i<lists.size();i++){
            LocalDateTime startTime = TimeUtil.localToUTC(lists.get(i).getStartTime());
            LocalDateTime endTime = TimeUtil.localToUTC(lists.get(i).getEndTime());
            System.out.println(LocalDateTime.now()+"%$^%$&^&^%&%^&"+startTime+LocalDateTime.now().isAfter(endTime));
            if(LocalDateTime.now().isAfter(startTime)||LocalDateTime.now().isAfter(endTime)){
                return new Result().fail("添加失败,你的时间已过期，请向前看");
            }
            Goods goods = goodsMapper.selectAllById(lists.get(i).getGoodsId());
            StoreGoods storeGoods = storeGoodsMapper.selectBygoodsId(lists.get(i).getGoodsId());;
            Business business = businessMapper.selectByBussinessId(storeGoods.getBusinessId());
            TypeGoods typeGoods = typeGoodsMapper.selectByGoodsId(lists.get(i).getGoodsId());;
            Sort sort = sortMapper.selectBySortId(typeGoods.getSortId());
            LocalDateTime start = LocalDateTime.now();
            Duration duration = Duration.between(start,endTime);
            System.out.println("*%&&&&%*"+duration.toMinutes()*60+"秒过期");
            if(TypeConsts.TYPE_CLOTHES.equals(sort.getSortEnglishName())){
                TypeClothes typeClothes = typeClothesMapper.selectByGoodsId(lists.get(i).getGoodsId());;
                SortListName sortListName = sortListNameMapper.selectById(typeGoods.getSortListNameId());
                SortListType sortListType = sortListTypeMapper.selectById(typeGoods.getSortListTypeId());
                List<String> imageDetail = Arrays.asList(typeClothes.getImageDetail().split(","));
                List<String> imageShow = Arrays.asList(typeClothes.getImageShow().split(","));
                List<String> color = Arrays.asList(typeClothes.getClothesColor().split(","));
                List<String> size = Arrays.asList(typeClothes.getClothesSize().split(","));
                GoodsDetail<TypeClothes> goodsAll = new GoodsDetail<>(goods,business.getStoreName(),TypeConsts.TYPE_CLOTHES, TypeJudgeUtil.judgeType(sortListName,sortListType),imageDetail,imageShow,color,size,typeClothes,lists.get(i).getStoreGoodsNumber(),lists.get(i).getStoreGoodsNumber(),endTime);
                redisUtil.set(VerifyConsts.SPIKE_CACHE_PREFIX+lists.get(i).getGoodsId(),goodsAll,duration.toMinutes()*60);
            }else if(TypeConsts.TYPE_SHOES.equals(sort.getSortEnglishName())){
                TypeShoes typeShoes = typeShoesMapper.selectByGoodsId(lists.get(i).getGoodsId());;
                List<String> imageDetail = Arrays.asList(typeShoes.getImageDetail().split(","));
                List<String> imageShow = Arrays.asList(typeShoes.getImageShow().split(","));
                List<String> color = Arrays.asList(typeShoes.getShoesColor().split(","));
                List<String> size = Arrays.asList(typeShoes.getShoesSize().split(","));
                GoodsDetail<TypeShoes> goodsAll = new GoodsDetail<>(goods,business.getStoreName(),TypeConsts.TYPE_SHOES,"shoes" ,imageDetail,imageShow,color,size,typeShoes,lists.get(i).getStoreGoodsNumber(),lists.get(i).getStoreGoodsNumber(),endTime);
                redisUtil.set(VerifyConsts.SPIKE_CACHE_PREFIX+lists.get(i).getGoodsId(),goodsAll,duration.toMinutes()*60);
            }else if(TypeConsts.TYPE_PACKAGE.equals(sort.getSortEnglishName())){
                TypePackage typePackage = typePackageMapper.selectByGoodsId(lists.get(i).getGoodsId());
                List<String> imageDetail = Arrays.asList(typePackage.getImageDetail().split(","));
                List<String> imageShow = Arrays.asList(typePackage.getImageShow().split(","));
                List<String> color = Arrays.asList(typePackage.getPackageColor().split(","));
                List<String> size = Arrays.asList(typePackage.getPackageSize().split(","));
                GoodsDetail<TypePackage> goodsAll = new GoodsDetail<>(goods,business.getStoreName(),TypeConsts.TYPE_PACKAGE,"package" ,imageDetail,imageShow,color,size,typePackage,lists.get(i).getStoreGoodsNumber(),lists.get(i).getStoreGoodsNumber(),endTime);
                redisUtil.set(VerifyConsts.SPIKE_CACHE_PREFIX+lists.get(i).getGoodsId(),goodsAll,duration.toMinutes()*60);
            }else if(TypeConsts.TYPE_ELECTRONIC.equals(sort.getSortEnglishName())){
                TypeElectronic typeElectronic = typeElectronicMapper.selectByGoodsId(lists.get(i).getGoodsId());;
                List<String> imageDetail = Arrays.asList(typeElectronic.getImageDetail().split(","));
                List<String> imageShow = Arrays.asList(typeElectronic.getImageShow().split(","));
                List<String> color = Arrays.asList(typeElectronic.getElectronicColor().split(","));
                List<String> size = Arrays.asList(typeElectronic.getElectronicFormat().split(","));
                GoodsDetail<TypeElectronic> goodsAll = new GoodsDetail<>(goods,business.getStoreName(),TypeConsts.TYPE_ELECTRONIC,"phone" ,imageDetail,imageShow,color,size,typeElectronic,lists.get(i).getStoreGoodsNumber(),lists.get(i).getStoreGoodsNumber(),endTime);
                redisUtil.set(VerifyConsts.SPIKE_CACHE_PREFIX+lists.get(i).getGoodsId(),goodsAll,duration.toMinutes()*60);
            }
            Spike spike = new Spike(lists.get(i).getGoodsId(),sort.getSortId(),storeGoods.getBusinessId(),goods.getGoodsPrePrice(),lists.get(i).getGoodsCurPrice(),lists.get(i).getStoreGoodsNumber(),lists.get(i).getStoreGoodsNumber(),startTime,endTime);
            spikeMapper.save(spike);
        }
        return new Result().success("添加成功!!!");
    }

    /**
     *获得需要修改的商品的部分原信息，方便进行降价
     * @param goodsId
     * @return
     */
    @Override
    public Result getPartNews(Long goodsId) {
        Spikes spikes = goodsMapper.selectPart(goodsId);
        spikes.setStartTime(null);
        spikes.setEndTime(null);
        return new Result().success(spikes,"查询到了!");
    }
}
