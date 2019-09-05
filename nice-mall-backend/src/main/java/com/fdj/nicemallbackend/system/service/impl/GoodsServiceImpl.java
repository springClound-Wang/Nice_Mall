package com.fdj.nicemallbackend.system.service.impl;

import com.fdj.nicemallbackend.common.utils.Base64tTransformUtil;
import com.fdj.nicemallbackend.common.utils.OssuploadUtil;
import com.fdj.nicemallbackend.system.dto.Findgoods;
import com.fdj.nicemallbackend.system.dto.Result;
import com.fdj.nicemallbackend.system.entity.*;
import com.fdj.nicemallbackend.system.mapper.*;
import com.fdj.nicemallbackend.system.service.IGoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.Store;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xns
 * @since 2019-08-31
 */
@Slf4j
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements IGoodsService {

    @Autowired
    GoodsMapper goodsMapper;

    @Autowired
    TypeClothesMapper typeClothesMapper;

    @Autowired
    TypeShoesMapper typeShoesMapper;

    @Autowired
    TypePackageMapper typePackageMapper;

    @Autowired
    TypeElectronicMapper typeElectronicMapper;

    @Autowired
    BusinessMapper businessMapper;

    @Autowired
    StoreGoodsMapper storeGoodsMapper;


    /**
     * 保存到商品表
     * @param map
     * @return
     */
    public long saveTOGoods(Map<String,Object> map){
        Long index = 0L;
        OssuploadUtil ossuploadUtil = new OssuploadUtil();
        Result imageMain = ossuploadUtil.uploadReturnUrl((List<String>)map.get("imageMain"));
        if(imageMain.isStatus()) {
            Goods goods = new Goods((String) map.get("goodsName"), (String) map.get("goodsDesc"), new BigDecimal((String) map.get("goodsPrePrice")), new BigDecimal((String) map.get("goodsCurPrice")), (String) map.get("goodsBrand"), (String) map.get("goodsPlace"), (String) imageMain.getData());
            goodsMapper.save(goods);
            index = goods.getGoodsId();
        }
        return index;
    }


    /**
     * 保存衣服类型的
     * @param map
     * @return
     */
    @Override
    public Result saveToclothes(Map<String, Object> map) {
        Long res = saveTOGoods(map);
        if(res!=0){
            List<String> list = (List<String>)map.get("clothesSize");
            String size = StringUtils.join(list.toArray(),",");
            OssuploadUtil ossuploadUtil = new OssuploadUtil();
            Result imageShow = ossuploadUtil.uploadReturnUrl((List<String>)map.get("imageShow"));
            Result imageDetail = ossuploadUtil.uploadReturnUrl((List<String>)map.get("imageDetail"));
            if(imageShow.isStatus()&&imageDetail.isStatus()) {
                TypeClothes typeClothes = new TypeClothes(res, size, (String) map.get("clothesSeason"), (String) map.get("clothesPerson"), (String) imageShow.getData(), (String) imageDetail.getData());
                typeClothesMapper.save(typeClothes);
                Business business = businessMapper.selectByuserId(Long.valueOf((String)map.get("userId")));
                StoreGoods storeGoods = new StoreGoods(business.getBusinessId(),res,Long.valueOf((String)map.get("storeGoodsNumber")),0L);
                storeGoodsMapper.save(storeGoods);
            }
            else{
                log.error("图片上传失败");
                return new Result().fail("添加失败!!!");
            }
        }
        return new Result().success(res,"添加成功!!!");
    }

    /**
     * 鞋子类型的
     * @param map
     * @return
     */
    @Override
    public Result saveToshoes(Map<String, Object> map) {
        Long res = saveTOGoods(map);
        if(res!=0){
            List<String> list = (List<String>)map.get("shoesSize");
            String size = StringUtils.join(list.toArray(),",");

            List<String> listc = (List<String>)map.get("shoesColor");
            String color = StringUtils.join(listc.toArray(),",");
            OssuploadUtil ossuploadUtil = new OssuploadUtil();
            Result imageShow = ossuploadUtil.uploadReturnUrl((List<String>)map.get("imageShow"));
            Result imageDetail = ossuploadUtil.uploadReturnUrl((List<String>)map.get("imageDetail"));
            if(imageShow.isStatus()&&imageDetail.isStatus()) {
                TypeShoes typeShoes = new TypeShoes(res, size, color,(String) map.get("shoesSeason"),(String)map.get("shoesPlace"),(String)map.get("shoesMaterial"),(String) imageShow.getData(), (String) imageDetail.getData());
                typeShoesMapper.save(typeShoes);
                Business business = businessMapper.selectByuserId(Long.valueOf((String)map.get("userId")));
                StoreGoods storeGoods = new StoreGoods(business.getBusinessId(),res,Long.valueOf((String)map.get("storeGoodsNumber")),0L);
                storeGoodsMapper.save(storeGoods);
            }
            else{
                log.error("图片上传失败");
                return new Result().fail("添加失败!!!");
            }
        }
        return new Result().success(res,"添加成功!!!");
    }

    /**
     * 包包类型的
     * @param map
     * @return
     */
    @Override
    public Result saveTopackage(Map<String, Object> map) {
        Long res = saveTOGoods(map);
        if(res!=0){
            List<String> list = (List<String>)map.get("packageSize");
            String size = StringUtils.join(list.toArray(),",");

            List<String> listc = (List<String>)map.get("packageColor");
            String color = StringUtils.join(listc.toArray(),",");

            OssuploadUtil ossuploadUtil = new OssuploadUtil();
            Result imageShow = ossuploadUtil.uploadReturnUrl((List<String>)map.get("imageShow"));
            Result imageDetail = ossuploadUtil.uploadReturnUrl((List<String>)map.get("imageDetail"));
            if(imageShow.isStatus()&&imageDetail.isStatus()) {
                TypePackage typePackage = new TypePackage(res, size,color,(String) map.get("packageSash"),(String)map.get("packageSex"),(String) map.get("packageSeason"), (String) map.get("packageWeight"),(String)map.get("packageMaterial"), (String) imageShow.getData(), (String) imageDetail.getData());
                typePackageMapper.save(typePackage);
                Business business = businessMapper.selectByuserId(Long.valueOf((String)map.get("userId")));
                StoreGoods storeGoods = new StoreGoods(business.getBusinessId(),res,Long.valueOf((String)map.get("storeGoodsNumber")),0L);
                storeGoodsMapper.save(storeGoods);
            }
            else{
                log.error("图片上传失败");
                return new Result().fail("添加失败!!!");
            }
        }
        return new Result().success(res,"添加成功!!!");
    }

    /**
     * 电子产品
     * @param map
     * @return
     */
    @Override
    public Result saveToelectr(Map<String, Object> map) {
        Long res = saveTOGoods(map);
        if(res!=0){
            List<String> list = (List<String>)map.get("electronicFormat");
            String format = StringUtils.join(list.toArray(),",");

            List<String> listc = (List<String>)map.get("electronicColor");
            String color = StringUtils.join(listc.toArray(),",");

            OssuploadUtil ossuploadUtil = new OssuploadUtil();
            Result imageShow = ossuploadUtil.uploadReturnUrl((List<String>)map.get("imageShow"));
            Result imageDetail = ossuploadUtil.uploadReturnUrl((List<String>)map.get("imageDetail"));
            if(imageShow.isStatus()&&imageDetail.isStatus()) {
                TypeElectronic typeElectronic = new TypeElectronic(res,color,format,(String) map.get("electronicSystem"), (String) map.get("electronicDoubleCard"),(String)map.get("electronicResolution"),(String)map.get("electronicFront"),(String)map.get("electronicBackend"),(String)map.get("electronicScreenSize"),(String)map.get("phoneFitting"),(String) imageShow.getData(), (String) imageDetail.getData());
                typeElectronicMapper.save(typeElectronic);
                Business business = businessMapper.selectByuserId(Long.valueOf((String)map.get("userId")));
                StoreGoods storeGoods = new StoreGoods(business.getBusinessId(),res,Long.valueOf((String)map.get("storeGoodsNumber")),0L);
                storeGoodsMapper.save(storeGoods);
            }
            else{
                log.error("图片上传失败");
                return new Result().fail("添加失败!!!");
            }
        }
        return new Result().success(res,"添加成功!!!");
    }

    /**
     * 根据字段模糊查询商品
     * @param field
     * @return
     */
    @Override
    public List<Findgoods> findByField(String field) {
        List<Findgoods> goods = goodsMapper.selectFuzzyByfiled(field);
        return goods;
    }
}
