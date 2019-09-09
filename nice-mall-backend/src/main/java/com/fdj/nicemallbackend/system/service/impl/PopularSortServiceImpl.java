package com.fdj.nicemallbackend.system.service.impl;

import com.fdj.nicemallbackend.common.utils.OssuploadUtil;
import com.fdj.nicemallbackend.system.dto.Result;
import com.fdj.nicemallbackend.system.entity.PopularSort;
import com.fdj.nicemallbackend.system.entity.SortImage;
import com.fdj.nicemallbackend.system.mapper.PopularSortMapper;
import com.fdj.nicemallbackend.system.mapper.SortImageMapper;
import com.fdj.nicemallbackend.system.service.IPopularSortService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
@Service
public class PopularSortServiceImpl extends ServiceImpl<PopularSortMapper, PopularSort> implements IPopularSortService {

    @Autowired
    PopularSortMapper popularSortMapper;

    @Autowired
    SortImageMapper sortImageMapper;


    /**
     * 添加热门分类及主图
     * @param map
     * @return
     */
    @Override
    public Result saveData(Map<String, Object> map) {
        OssuploadUtil ossuploadUtil = new OssuploadUtil();
        Result imageMains = ossuploadUtil.uploadReturnUrl((List<String>) map.get("imageMain"));
        if (imageMains.isStatus()) {
            SortImage sortImage = new SortImage((String) map.get("uploadType"), (String) imageMains.getData());
            sortImageMapper.save(sortImage);
            List<String> typeNames = (List<String>) map.get("typename");
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
        } else {
            return new Result().fail("主图片添加失败!");
        }
        return new Result().success("添加成功,哈哈哈!!!");
    }
}
