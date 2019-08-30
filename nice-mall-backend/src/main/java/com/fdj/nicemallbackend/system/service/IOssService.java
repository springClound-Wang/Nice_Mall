package com.fdj.nicemallbackend.system.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Classname IOssService
 * @Description TODO
 * @Date 19-8-29 下午6:52
 * @Created by xns
 */
public interface IOssService {
    String updateHead(MultipartFile file) throws Exception;
}
