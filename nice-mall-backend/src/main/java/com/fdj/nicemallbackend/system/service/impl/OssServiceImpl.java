package com.fdj.nicemallbackend.system.service.impl;

import com.fdj.nicemallbackend.common.utils.OSSClientUtil;
import com.fdj.nicemallbackend.system.service.IOssService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Classname OssServiceImpl
 * @Description TODO
 * @Date 19-8-29 下午6:53
 * @Created by xns
 */
@Service
public class OssServiceImpl implements IOssService {
    @Override
    public String updateHead(MultipartFile file) throws Exception {
        if(file == null || file.getSize() <= 0){
            throw new Exception("file不能为空");
        }
        OSSClientUtil ossClient = new OSSClientUtil();
        String name = ossClient.uploadImg2Oss(file);
        String imgUrl = ossClient.getImgUrl(name);
        String[] split = imgUrl.split("\\?");
        return split[0];
    }
}
