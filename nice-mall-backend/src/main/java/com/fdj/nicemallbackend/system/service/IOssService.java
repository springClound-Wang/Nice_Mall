package com.fdj.nicemallbackend.system.service;

/**
 * @Classname IOssService
 * @Description TODO
 * @Date 19-8-27 下午4:09
 * @Created by xns
 */
import com.fdj.nicemallbackend.system.dto.OssCallbackResult;
import com.fdj.nicemallbackend.system.dto.OssPolicyResult;

import javax.servlet.http.HttpServletRequest;

/**
 * oss上传管理Service
 * Created by macro on 2018/5/17.
 */
public interface IOssService {
    /**
     * oss上传策略生成
     */
    OssPolicyResult policy();

    /**
     * oss上传成功回调
     */
    OssCallbackResult callback(HttpServletRequest request);
}
