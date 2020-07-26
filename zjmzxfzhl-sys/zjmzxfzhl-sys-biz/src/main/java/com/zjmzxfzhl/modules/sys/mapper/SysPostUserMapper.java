package com.zjmzxfzhl.modules.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zjmzxfzhl.modules.sys.entity.SysPostUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 岗位和用户关系Mapper
 *
 * @author 庄金明
 */
public interface SysPostUserMapper extends BaseMapper<SysPostUser> {
    /**
     * 查询岗位和用户关系列表
     *
     * @param page
     * @param entity
     * @return
     */
    List<SysPostUser> list(IPage<SysPostUser> page, @Param("entity") SysPostUser entity);
}
