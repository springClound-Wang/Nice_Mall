package com.fdj.nicemallbackend.common.utils;

import com.fdj.nicemallbackend.system.dto.Result;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Classname OssuploadUtil
 * @Description TODO
 * @Date 19-8-31 下午1:59
 * @Created by xns
 */
public class OssuploadUtil {

    public String updateHead(MultipartFile file) throws Exception {
        if (file == null || file.getSize() <= 0) {
            throw new Exception("file不能为空");
        }
        OSSClientUtil ossClient = new OSSClientUtil();
        String name = ossClient.uploadImg2Oss(file);
        String imgUrl = ossClient.getImgUrl(name);
        String[] split = imgUrl.split("\\?");
        return split[0];
    }

    /**
     * 上传多张图片，然后拼接所有的url
     *
     * @param image
     * @return
     */
    public Result uploadReturnUrl(List<String> image) {
        StringBuilder imageStr = new StringBuilder();
        for (int i = 0; i < image.size(); i++) {
            MultipartFile file = Base64tTransformUtil.base64ToMultipart(image.get(i));
            try {
                String url = this.updateHead(file);
                System.out.println("第" + (i + 1) + "张图片路径: " + url);
                if (i + 1 == image.size()) {
                    imageStr.append(url);
                } else {
                    imageStr.append(url + ",");
                }
            } catch (Exception e) {
                e.printStackTrace();
                return new Result().fail("图片上传失败!!!");
            }
        }
        return new Result().success(imageStr.toString(), "图片上传成功");
    }


    /**
     * 上传一张图片
     *
     * @param image
     * @return
     */
    public Result oneuploadReturnUrl(String image) {
        String url;
        MultipartFile file = Base64tTransformUtil.base64ToMultipart(image);
        try {
            url = this.updateHead(file);
            System.out.println("图片路径: " + url);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result().fail("图片上传失败!!!");
        }
        return new Result().success(url, "图片上传成功");
    }
}
