package com.zjmzxfzhl.modules.sys.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjmzxfzhl.common.core.Result;
import com.zjmzxfzhl.common.core.base.BaseController;
import com.zjmzxfzhl.common.log.annotation.Log;
import com.zjmzxfzhl.modules.sys.entity.SysFunc;
import com.zjmzxfzhl.modules.sys.service.SysFuncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;

/**
 * 功能Controller
 * 
 * @author 庄金明
 */
@RestController
@RequestMapping("/sys/func")
public class SysFuncController extends BaseController {
    @Autowired
    private SysFuncService sysFuncService;

    /**
     * 自定义查询列表
     * 
     * @param sysFunc
     * @param current
     * @param size
     * @return
     */
    @PreAuthorize("@elp.single('sys:func:list')")
    @GetMapping(value = "/list")
    public Result list(SysFunc sysFunc, @RequestParam Integer current, @RequestParam Integer size) {
        IPage<SysFunc> pageList = sysFuncService.list(new Page<SysFunc>(current, size), sysFunc);
        return Result.ok(pageList);
    }

    @PreAuthorize("@elp.single('sys:func:list')")
    @GetMapping(value = "/queryById")
    public Result queryById(@RequestParam String id) {
        SysFunc sysFunc = sysFuncService.getById(id);
        return Result.ok(sysFunc);
    }

    /**
     * @功能：新增
     * @param sysFunc
     * @return
     */
    @Log(value = "新增功能按钮")
    @PreAuthorize("@elp.single('sys:func:save')")
    @PostMapping(value = "/save")
    public Result save(@Valid @RequestBody SysFunc sysFunc) {
        sysFuncService.save(sysFunc);
        return Result.ok();
    }

    /**
     * @功能：修改
     * @param sysFunc
     * @return
     */
    @Log(value = "修改功能按钮")
    @PreAuthorize("@elp.single('sys:func:update')")
    @PutMapping(value = "/update")
    public Result update(@Valid @RequestBody SysFunc sysFunc) {
        sysFuncService.updateById(sysFunc);
        return Result.ok();
    }

    /**
     * @功能：批量删除
     * @param ids
     * @return
     */
    @Log(value = "删除功能按钮")
    @PreAuthorize("@elp.single('sys:func:delete')")
    @DeleteMapping(value = "/delete")
    public Result delete(@RequestParam String ids) {
        sysFuncService.delete(ids);
        return Result.ok();
    }
}
