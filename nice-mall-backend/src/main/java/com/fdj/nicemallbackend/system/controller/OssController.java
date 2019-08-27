package com.fdj.nicemallbackend.system.controller;

import com.fdj.nicemallbackend.system.dto.OssCallbackResult;
import com.fdj.nicemallbackend.system.dto.OssPolicyResult;
import com.fdj.nicemallbackend.system.dto.Result;
import com.fdj.nicemallbackend.system.service.impl.OssServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Classname OssController
 * @Description TODO
 * @Date 19-8-27 下午4:59
 * @Created by xns
 * Oss相关操作接口
 */

@RestController
@RequestMapping("/aliyun/oss")
public class OssController {
    @Autowired
    private OssServiceImpl ossService;

    /**
     * oss上传签名生成
     */
    @RequestMapping(value = "/policy", method = RequestMethod.GET)
    public Result policy() {
        OssPolicyResult result = ossService.policy();
        return new Result().success(result);
    }

    /**
     * oss上传成功回调
     */
    @RequestMapping(value = "callback", method = RequestMethod.POST)
    public Result callback(HttpServletRequest request) {
        OssCallbackResult ossCallbackResult = ossService.callback(request);
        return new Result().success(ossCallbackResult);
    }
}
