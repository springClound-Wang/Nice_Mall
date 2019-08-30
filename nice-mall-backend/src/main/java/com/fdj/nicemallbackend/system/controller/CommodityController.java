package com.fdj.nicemallbackend.system.controller;

import com.fdj.nicemallbackend.common.utils.Base64tTransformUtil;
import com.fdj.nicemallbackend.system.dto.Result;
import com.fdj.nicemallbackend.system.service.IOssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * @Classname CommodityController
 * @Description TODO
 * @Date 19-8-30 上午11:10
 * @Created by xns
 */
@RestController
public class CommodityController {

    @Autowired
    IOssService iOssService;

    @PostMapping("/addcommdity")
    public Result Addcommdity(@RequestBody Map<String,String> map){
        MultipartFile file = Base64tTransformUtil.base64ToMultipart(map.get("base64Str"));
        try {
            String url = iOssService.updateHead(file);
            System.out.println("图片路径:== "+url);
            return new Result().success(url,"图片上传成功");

        } catch (Exception e) {
            e.printStackTrace();
            return new Result().fail("图片上传失败!!!");
        }
    }
}
