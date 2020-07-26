package com.zjmzxfzhl.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zjmzxfzhl.common.core.Result;
import com.zjmzxfzhl.common.core.base.BaseServiceImpl;
import com.zjmzxfzhl.common.core.exception.SysException;
import com.zjmzxfzhl.common.core.util.CommonUtil;
import com.zjmzxfzhl.modules.sys.entity.SysFunc;
import com.zjmzxfzhl.modules.sys.entity.SysRolePermission;
import com.zjmzxfzhl.modules.sys.mapper.SysFuncMapper;
import com.zjmzxfzhl.modules.sys.service.SysFuncService;
import com.zjmzxfzhl.modules.sys.service.SysRolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

/**
 * 功能Service
 * 
 * @author 庄金明
 */
@Service
public class SysFuncServiceImpl extends BaseServiceImpl<SysFuncMapper, SysFunc> implements SysFuncService {

    @Autowired
    private SysRolePermissionService sysRolePermissionService;

    @Override
    public IPage<SysFunc> list(IPage<SysFunc> page, SysFunc sysFunc) {
        return page.setRecords(baseMapper.list(page, sysFunc));
    }

    /**
     * 删除功能按钮
     * @param ids
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(String ids){
        CommonUtil.isEmptyStr(ids,"ids can't be empty");
        String[] idsArr = ids.split(",");
        if (idsArr.length > 1) {
            removeByIds(Arrays.asList(idsArr));
        } else {
            removeById(idsArr[0]);
        }
        sysRolePermissionService.remove(new LambdaQueryWrapper<SysRolePermission>().in(SysRolePermission::getMenuOrFuncId, idsArr));
        return true;
    }
}
