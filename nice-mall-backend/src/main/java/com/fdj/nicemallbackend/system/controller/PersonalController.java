package com.fdj.nicemallbackend.system.controller;

import com.fdj.nicemallbackend.common.authenication.Jwt_Get;
import com.fdj.nicemallbackend.common.utils.OssuploadUtil;
import com.fdj.nicemallbackend.system.dto.Result;
import com.fdj.nicemallbackend.system.entity.ReceiptAddress;
import com.fdj.nicemallbackend.system.entity.User;
import com.fdj.nicemallbackend.system.service.IPersonalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @Classname PersonalController
 * @Description TODO
 * @Date 19-10-13 上午11:08
 * @Created by xns
 */
@Slf4j
@RestController
@RequestMapping("/personal")
public class PersonalController {

    @Autowired
    IPersonalService iPersonalService;


    /**
     * 获取个人信息
     * @param userId
     * @return
     */
    @GetMapping("/getdata")
    public Result getPersonalNews(@RequestParam Long userId){
        Result result = iPersonalService.getPersonal(userId);
        return result;
    }

    /**
     * 修改个人资料
     * @param map
     * @return
     */
    @PutMapping("/change")
    public Result changePersonalInformation(@RequestBody Map<String,Object> map){
        int flag=0;
        Result res;
        List<String> images = (List<String>)map.get("userAvatar");
        if(images.size()!=0) {
            flag=1;
            String image = images.get(0);
            OssuploadUtil ossuploadUtil = new OssuploadUtil();
            res = ossuploadUtil.oneuploadReturnUrlToPoint(image, "mall/images/Avatar/");
        }else{
            res = new Result().success("ennn");
        }
        if(res.isStatus()){
            String userAvatar = (String)res.getData();
            User user = new User(Long.valueOf(String.valueOf(map.get("userId"))),(String)map.get("userName"),(String)map.get("userTrueName"),userAvatar,(String)map.get("userSex"),(String)map.get("userBirth"),(String)map.get("userAddress"));
            iPersonalService.changeNew(user,flag);
        }else{
            log.error("图片上传服务器失败!");
            return new Result().fail("更新失败!");
        }
        return new Result().success("信息修改成功!");
    }

    /**
     * 添加收货地址信息
     * @param receiptAddress
     * @return
     */
    @PostMapping("/address")
    public Result addReceiptAddress(@RequestBody ReceiptAddress receiptAddress){
        Result result = iPersonalService.addAddress(receiptAddress);
        return result;
    }

    /**
     * 用户获取所有的地址
     * @param userId
     * @return
     */
    @GetMapping("/getaddr")
    public Result getAddress(@RequestParam Long userId){
         Result result = iPersonalService.getAllAddress(userId);
         return result;
    }


    /**
     * 删除某个收货地址
     * @param addressId
     * @return
     */
    @DeleteMapping("/deladdr")
    public Result deleteAddress(@RequestParam Long addressId, HttpServletRequest request){
        System.out.println("用户id"+Jwt_Get.getUser(request));
        Result result = iPersonalService.deleteAddr(addressId);
        return result;
    }
}
