package com.zjmzxfzhl.modules.app.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zjmzxfzhl.modules.app.entity.AppUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户Mapper
 *
 * @author 庄金明
 */
public interface AppUserMapper extends BaseMapper<AppUser> {
    /**
     * 查询用户列表
     *
     * @param page
     * @param entity
     * @return
     */
    List<AppUser> list(IPage<AppUser> page, @Param("entity") AppUser entity);
}
