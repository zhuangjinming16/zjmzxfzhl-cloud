package com.zjmzxfzhl.modules.flowable.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zjmzxfzhl.modules.flowable.entity.FlowableForm;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 流程表单Mapper
 *
 * @author 庄金明
 */
public interface FlowableFormMapper extends BaseMapper<FlowableForm> {
    /**
     * 查询流程表单列表
     *
     * @param page
     * @param entity
     * @return
     */
    List<FlowableForm> list(IPage<FlowableForm> page, @Param("entity") FlowableForm entity);
}
