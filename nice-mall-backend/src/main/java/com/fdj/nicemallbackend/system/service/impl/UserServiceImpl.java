package com.fdj.nicemallbackend.system.service.impl;

import com.fdj.nicemallbackend.common.utils.RandomUtil;
import com.fdj.nicemallbackend.system.entity.Rolelist;
import com.fdj.nicemallbackend.system.entity.User;
import com.fdj.nicemallbackend.system.mapper.UserroleMapper;
import com.fdj.nicemallbackend.system.mapper.UserMapper;
import com.fdj.nicemallbackend.system.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xns
 * @since 2019-08-15
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserroleMapper userroleMapper;

    /**
     * 通过用户名查询信息
     *
     * @param username
     * @return
     */
    @Override
    public User getUser(String username) {
        return userMapper.findByName(username);
    }

    /**
     * 检验验证码并注册(暂且默认验证码可以随便填写且正确)
     *
     * @param telephone
     * @param password
     */
    @Override
    public Boolean regist(String telephone, String password, String code) {
        Boolean flag = true;
        User user = new User();
        user.setUserName(RandomUtil.achieveName(userMapper.selectAllName()));
        user.setUserPassword(password);
        user.setUserSex(User.SEX_UNKNOW);
        user.setUserTelephone(telephone);
        user.setUserAvatar(User.DEFAULT_AVATAR);
        userMapper.insertsave(user);

        Rolelist roleuser = new Rolelist();
        roleuser.setRoleId(2);
        user = userMapper.findByPhone(telephone);
        roleuser.setUserId(user.getUserId());
        userroleMapper.save(roleuser);
        return flag;
    }

    @Override
    public User getUserByphone(String telephone) {
        return userMapper.findByPhone(telephone);
    }

    @Override
    public List<User> getAllUser() {
        List<User> users = new ArrayList<>();
        users = userMapper.selectAll();
        for (int i = 0; i < users.size(); i++) {
            users.get(i).setUserPassword("---------");
        }
        return users;
    }
}
