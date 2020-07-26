package com.zjmzxfzhl.modules.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zjmzxfzhl.modules.sys.entity.SysConfig;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 系统参数Mapper
 *
 * @author 庄金明
 */
public interface SysConfigMapper extends BaseMapper<SysConfig> {
    /**
     * 查询系统参数列表
     *
     * @param page
     * @param entity
     * @return
     */
    List<SysConfig> list(IPage<SysConfig> page, @Param("entity") SysConfig entity);
}
