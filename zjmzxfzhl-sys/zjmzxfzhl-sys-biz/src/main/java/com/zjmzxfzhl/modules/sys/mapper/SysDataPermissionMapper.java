package com.zjmzxfzhl.modules.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zjmzxfzhl.modules.sys.entity.SysDataPermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 数据权限Mapper
 *
 * @author 庄金明
 */
public interface SysDataPermissionMapper extends BaseMapper<SysDataPermission> {
    /**
     * 查询数据权限列表
     *
     * @param page
     * @param entity
     * @return
     */
    List<SysDataPermission> list(IPage<SysDataPermission> page, @Param("entity") SysDataPermission entity);
}
