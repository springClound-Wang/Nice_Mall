package com.fdj.nicemallbackend.system.controller;

import com.fdj.nicemallbackend.common.utils.OSSClientUtil;
import com.fdj.nicemallbackend.common.utils.OssuploadUtil;
import com.fdj.nicemallbackend.system.dto.Result;
import com.fdj.nicemallbackend.system.entity.TypeEntry;
import com.fdj.nicemallbackend.system.service.ISelfAddService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * @Classname SelfaddController
 * @Description TODO
 * @Date 19-9-18 下午8:05
 * @Created by xns
 */
@RestController
@RequestMapping("/self")
public class SelfaddController {

    @Autowired
    ISelfAddService iSelfAddService;

    @PostMapping("/entry")
    public Result uploadEntry(@RequestBody Map<String,Object> map) throws Exception {
        Result image = new OssuploadUtil().oneuploadReturnUrl((String)map.get("entryImage"));
        TypeEntry typeEntry = new TypeEntry((String)map.get("entryName"),(String)map.get("entryTitle"),(String)image.getData(),(String)map.get("entryDiscount"));
        Result result = iSelfAddService.addEntry(typeEntry);
        return result;
    }

}
