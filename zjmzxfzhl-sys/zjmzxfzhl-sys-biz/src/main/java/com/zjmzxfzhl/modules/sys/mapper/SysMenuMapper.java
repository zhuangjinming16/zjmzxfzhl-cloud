package com.zjmzxfzhl.modules.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zjmzxfzhl.modules.sys.entity.SysMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 菜单Mapper
 *
 * @author 庄金明
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {
    /**
     * 查询菜单列表
     *
     * @param page
     * @param entity
     * @return
     */
    List<SysMenu> list(IPage<SysMenu> page, @Param("entity") SysMenu entity);
}
