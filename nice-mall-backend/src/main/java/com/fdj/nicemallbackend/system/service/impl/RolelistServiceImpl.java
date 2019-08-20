package com.fdj.nicemallbackend.system.service.impl;

import com.fdj.nicemallbackend.system.entity.Rolelist;
import com.fdj.nicemallbackend.system.mapper.RoleMapper;
import com.fdj.nicemallbackend.system.mapper.UserroleMapper;
import com.fdj.nicemallbackend.system.mapper.UserMapper;
import com.fdj.nicemallbackend.system.service.IRolelistService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xns
 * @since 2019-08-17
 */
@Service
public class RolelistServiceImpl extends ServiceImpl<UserroleMapper, Rolelist> implements IRolelistService {

    @Autowired
    UserroleMapper rolelistMapper;

    @Autowired
    RoleMapper roleMapper;

    @Autowired
    UserMapper userMapper;

    @Override
    public Set<String> getUserRoles(String username) {
        Set<String> roless = null;
        List<Integer> roles =  rolelistMapper.selectRolesByuid(userMapper.findByName(username).getUserId());
        for(int i=0;i<roles.size();i++){
            roless.add(roleMapper.selectByroleId(roles.get(i)));
        }
        return roless;
    }
}
