package com.fdj.nicemallbackend.system.service.impl;

import com.fdj.nicemallbackend.system.dto.Result;
import com.fdj.nicemallbackend.system.entity.TypeEntry;
import com.fdj.nicemallbackend.system.mapper.TypeEntryMapper;
import com.fdj.nicemallbackend.system.service.ISelfAddService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xns
 * @since 2019-09-18
 */
@Slf4j
@Service
public class SelfAddServiceImpl extends ServiceImpl<TypeEntryMapper, TypeEntry> implements ISelfAddService {

    @Autowired
    TypeEntryMapper typeEntryMapper;

    @Override
    public Result addEntry(TypeEntry typeEntry) {
        try {
            typeEntryMapper.insert(typeEntry);
        }catch(DuplicateKeyException e){
            log.error("类型和标题重复，不可添加");
            return new Result().fail("类型和标题重复，不可以添加");
        }
        return new Result().success("添加成功!!");
    }
}
