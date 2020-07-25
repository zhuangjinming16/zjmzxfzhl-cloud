package com.zjmzxfzhl.modules.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zjmzxfzhl.modules.sys.entity.SysOauthClientDetails;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 应用客户端Mapper
 * 
 * @author 庄金明
 */
public interface SysOauthClientDetailsMapper extends BaseMapper<SysOauthClientDetails> {
    /**
     * 查询应用客户端列表
     * 
     * @param page
     * @param entity
     * @return
     */
    List<SysOauthClientDetails> list(IPage<SysOauthClientDetails> page,
                                            @Param("entity") SysOauthClientDetails entity);
}
