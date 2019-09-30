package com.fdj.nicemallbackend.system.service.impl;

import com.fdj.nicemallbackend.common.domain.TypeConsts;
import com.fdj.nicemallbackend.common.utils.OssuploadUtil;
import com.fdj.nicemallbackend.system.dto.Findgoods;
import com.fdj.nicemallbackend.system.dto.Result;
import com.fdj.nicemallbackend.system.entity.*;
import com.fdj.nicemallbackend.system.mapper.*;
import com.fdj.nicemallbackend.system.service.IGoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.language.bm.NameType;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.type.ListType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

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

    @Autowired
    SortListTypeMapper sortListTypeMapper;

    @Autowired
    TypeGoodsMapper typeGoodsMapper;

    @Autowired
    SortMapper sortMapper;

    @Autowired
    SortListNameMapper sortListNameMapper;


    /**
     * 保存到商品表
     * @param map
     * @return
     */
    @Transactional
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
    @Transactional
    public Result saveToclothes(Map<String, Object> map) {
        Long res = saveTOGoods(map);
        if(res!=0){
            List<String> list = (List<String>)map.get("clothesSize");
            String size = StringUtils.join(list.toArray(),",");

            String color = (String)map.get("clothesColor");

            OssuploadUtil ossuploadUtil = new OssuploadUtil();
            Result imageShow = ossuploadUtil.uploadReturnUrl((List<String>)map.get("imageShow"));
            Result imageDetail = ossuploadUtil.uploadReturnUrl((List<String>)map.get("imageDetail"));
            if(imageShow.isStatus()&&imageDetail.isStatus()) {
                TypeClothes typeClothes = new TypeClothes(res, size, color,(String) map.get("clothesSeason"), (String) map.get("clothesPerson"), (String) imageShow.getData(), (String) imageDetail.getData());
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
    @Transactional
    public Result saveToshoes(Map<String, Object> map) {
        Long res = saveTOGoods(map);
        if(res!=0){
            List<String> list = (List<String>)map.get("shoesSize");
            String size = StringUtils.join(list.toArray(),",");

           /* List<String> listc = (List<String>)map.get("shoesColor");
            String color = StringUtils.join(listc.toArray(),",");*/

            String color = (String)map.get("shoesColor");
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
    @Transactional
    public Result saveTopackage(Map<String, Object> map) {
        Long res = saveTOGoods(map);
        if(res!=0){
            List<String> list = (List<String>)map.get("packageSize");
            String size = StringUtils.join(list.toArray(),",");

//            List<String> listc = (List<String>)map.get("packageColor");
//            String color = StringUtils.join(listc.toArray(),",");
            String color = (String)map.get("packageColor");

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
    @Transactional
    public Result saveToelectr(Map<String, Object> map) {
        Long res = saveTOGoods(map);
        if(res!=0){
            List<String> list = (List<String>)map.get("electronicFormat");
            String format = StringUtils.join(list.toArray(),",");

//            List<String> listc = (List<String>)map.get("electronicColor");
//            String color = StringUtils.join(listc.toArray(),",");

            String color = (String)map.get("electronicColor");

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
    public Set<Findgoods> findByField(String field) {
        List<Integer> type = sortListTypeMapper.selectPartId(field);
        Set<Findgoods> goods = new HashSet<>();
        if(!type.isEmpty()) {
            for(int i=0;i<type.size();i++) {
                List<Long> goodsIds = new ArrayList<>();
                goodsIds = typeGoodsMapper.selectBytypeId(type.get(i));
                for(int j=0;j<goodsIds.size();j++){
                    Findgoods good = goodsMapper.selectById(goodsIds.get(j));
                    goods.add(good);
                }
            }
        }
        List<Findgoods> goodspart = goodsMapper.selectFuzzyByfiled(field);
        for(int i=0;i<goodspart.size();i++){
            goods.add(goodspart.get(i));
        }
        return goods;
    }



    /**
     * 得到某商品的详细信息
     * @param goodsId
     * @return
     */
    @Override
    public Result showOneDetail(Long goodsId) {
        Goods goods = goodsMapper.selectAllById(goodsId);
        if(goods==null){
            return new Result().fail("未查询到此商品");
        }
        Map <String,Object> map = new HashMap<>();
        TypeGoods typeGoods = typeGoodsMapper.selectByGoodsId(goodsId);
        StoreGoods storeGoods = storeGoodsMapper.selectBygoodsId(goodsId);
        Business business = businessMapper.selectByBussinessId(storeGoods.getBusinessId());
        Sort sort = sortMapper.selectById(typeGoods.getSortId());
        map.put("goodsMain",goods);
        map.put("storeGoods",business.getStoreName());
        if(sort.getSortEnglishName().equals(TypeConsts.TYPE_SHOES)){
            TypeShoes typeShoes = typeShoesMapper.selectByGoodsId(goodsId);
            map.put("goodsType","shoes");
            map.put("goodsFlag","shoes");
            List<String> imageDetail = Arrays.asList(typeShoes.getImageDetail().split(","));
            List<String> imageShow = Arrays.asList(typeShoes.getImageShow().split(","));
            List<String> goodsColor = Arrays.asList(typeShoes.getShoesColor().split(","));
            List<String> goodsSize = Arrays.asList(typeShoes.getShoesSize().split(","));
            map.put("goodsDetail",typeShoes);
            map.put("imageDetail",imageDetail);
            map.put("imageShow",imageShow);
            map.put("color",goodsColor);
            map.put("size",goodsSize);
        }
        if(sort.getSortEnglishName().equals(TypeConsts.TYPE_ELECTRONIC)){
            TypeElectronic typeElectronic = typeElectronicMapper.selectByGoodsId(goodsId);
            map.put("goodsFlag","phone");
            map.put("goodsType","electronic");
            List<String> imageDetail = Arrays.asList(typeElectronic.getImageDetail().split(","));
            List<String> imageShow = Arrays.asList(typeElectronic.getImageShow().split(","));
            List<String> electronicColor = Arrays.asList(typeElectronic.getElectronicColor().split(","));
            List<String> electronicFormat = Arrays.asList(typeElectronic.getElectronicFormat().split(","));
            map.put("goodsDetail",typeElectronic);
            map.put("imageDetail",imageDetail);
            map.put("imageShow",imageShow);
            map.put("color",electronicColor);
            map.put("size",electronicFormat);
        }
        if(sort.getSortEnglishName().equals(TypeConsts.TYPE_PACKAGE)){
            TypePackage typePackage = typePackageMapper.selectByGoodsId(goodsId);
            map.put("goodsFlag","package");
            map.put("goodsType","package");
            List<String> imageDetail = Arrays.asList(typePackage.getImageDetail().split(","));
            List<String> imageShow = Arrays.asList(typePackage.getImageShow().split(","));
            List<String> packageColor = Arrays.asList(typePackage.getPackageColor().split(","));
            List<String> packageSize = Arrays.asList(typePackage.getPackageSize().split(","));
            map.put("imageDetail",imageDetail);
            map.put("imageShow",imageShow);
            map.put("color",packageColor);
            map.put("size",packageSize);
            map.put("goodsDetail",typePackage);
        }
        if(sort.getSortEnglishName().equals(TypeConsts.TYPE_CLOTHES)){
            map.put("goodsType","clothes");
            SortListName sortListName = sortListNameMapper.selectById(typeGoods.getSortListNameId());
            SortListType sortListType = sortListTypeMapper.selectById(typeGoods.getSortListTypeId());
            TypeClothes typeClothes = typeClothesMapper.selectByGoodsId(goodsId);
            if(sortListName.getSortListName().contains(("裤"))) {
                if (sortListType.getSortListName().contains("女")) {
                    map.put("goodsFlag","clothesDownWoman");
                }else if(sortListType.getSortListName().contains("男")){
                    map.put("goodsFlag","clothesDownMen");
                }
            }
            else{
                if(sortListType.getSortListName().contains("裙")){
                    map.put("goodsFlag","clothesSkirt");
                }
                else if(sortListName.getSortListName().contains("女")){
                    map.put("goodsFlag","clothesUpWoman");
                }
                else if(sortListName.getSortListName().contains("男")){
                    map.put("goodsFlag","clothesUpMen");
                }
                else{
                    map.put("goodsFlag","clothesUpWoman");
                }
            }
            List<String> imageDetail = Arrays.asList(typeClothes.getImageDetail().split(","));
            List<String> imageShow = Arrays.asList(typeClothes.getImageShow().split(","));
            List<String> clothesColor = Arrays.asList(typeClothes.getClothesColor().split(","));
            List<String> clothesSize = Arrays.asList(typeClothes.getClothesSize().split(","));
            map.put("imageDetail",imageDetail);
            map.put("imageShow",imageShow);
            map.put("color",clothesColor);
            map.put("size",clothesSize);
            map.put("goodsDetail",typeClothes);
        }
        return new Result().success(map,"查询到了!!!");
    }

    /**
     * 点击阶级类型获得商品
     * @param typefield
     * @return
     */
    @Override
    public Set<Findgoods> findBySortType(String typefield) {
        String[] a = StringUtils.split(typefield,">");
        SortListName sortNameType  = sortListNameMapper.selectId(a[0]);
        SortListType sortListType = sortListTypeMapper.selectId(sortNameType.getSortListId(),a[1]);
        List<Long> goodsIds = new ArrayList<>();
        Set<Findgoods> goods = new HashSet<>();
        goodsIds = typeGoodsMapper.selectBytypeId(sortListType.getSortListTypeId());
        for(int j=0;j<goodsIds.size();j++){
            Findgoods findgoods = goodsMapper.selectById(goodsIds.get(j));
            goods.add(findgoods);
        }
        return goods;
    }
}
