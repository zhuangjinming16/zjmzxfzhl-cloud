package com.zjmzxfzhl.modules.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zjmzxfzhl.modules.sys.entity.SysCodeInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 代码信息Mapper
 * 
 * @author 庄金明
 */
public interface SysCodeInfoMapper extends BaseMapper<SysCodeInfo> {
    /**
     * 查询代码信息列表
     * 
     * @param page
     * @param entity
     * @return
     */
    List<SysCodeInfo> list(IPage<SysCodeInfo> page, @Param("entity") SysCodeInfo entity);
}
