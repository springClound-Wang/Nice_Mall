package com.fdj.nicemallbackend.system.service.impl;

import com.fdj.nicemallbackend.common.utils.OssuploadUtil;
import com.fdj.nicemallbackend.common.utils.judgeUtil;
import com.fdj.nicemallbackend.system.dto.Result;
import com.fdj.nicemallbackend.system.entity.PopularSort;
import com.fdj.nicemallbackend.system.entity.SortImage;
import com.fdj.nicemallbackend.system.mapper.PopularSortMapper;
import com.fdj.nicemallbackend.system.mapper.SortImageMapper;
import com.fdj.nicemallbackend.system.service.IPopularSortService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xns
 * @since 2019-09-08
 */
@Slf4j
@Service
public class PopularSortServiceImpl extends ServiceImpl<PopularSortMapper, PopularSort> implements IPopularSortService {

    @Autowired
    PopularSortMapper popularSortMapper;

    @Autowired
    SortImageMapper sortImageMapper;


    /**
     * 添加热门分类及主图
     *
     * @param map
     * @return
     */
    @Override
    @Transactional
    public Result saveData(Map<String, Object> map) {
        if (judgeUtil.isEquals((List<String>) map.get("imageDetails"))) {
            return new Result().fail("添加的小图片中有重复哦,请慎重添加!!");
        }
        OssuploadUtil ossuploadUtil = new OssuploadUtil();
        Result imageMains = ossuploadUtil.uploadReturnUrl((List<String>) map.get("imageMains"));
        if (imageMains.isStatus()) {
            try {
                SortImage sortImage = new SortImage((String) map.get("uploadType"), (String) imageMains.getData());
                sortImageMapper.save(sortImage);
                List<String> typeNames = (List<String>) map.get("typeName");
                List<String> imageDetails = (List<String>) map.get("imageDetails");
                for (int i = 0; i < typeNames.size(); i++) {
                    Result imageUrl = ossuploadUtil.oneuploadReturnUrl(imageDetails.get(i));
                    if (imageUrl.isStatus()) {
                        PopularSort popularSort = new PopularSort(sortImage.getImageMainId(), typeNames.get(i), (String) imageUrl.getData());
                        popularSortMapper.save(popularSort);
                    } else {
                        return new Result().fail("某小图片上传失败!");
                    }
                }
            } catch (DuplicateKeyException e) {
                log.error("大类型，大图片重复，之前已经添加过!");
                return new Result().fail("此次的大类型，大图片重复，之前已经添加过!");
            }
        } else {
            return new Result().fail("主图片添加失败!");
        }
        return new Result().success("添加成功,哈哈哈!!!");
    }
}
