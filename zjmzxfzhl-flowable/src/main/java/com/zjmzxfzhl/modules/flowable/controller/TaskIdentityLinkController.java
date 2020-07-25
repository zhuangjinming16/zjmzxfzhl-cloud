package com.zjmzxfzhl.modules.flowable.controller;

import com.zjmzxfzhl.common.core.Result;
import com.zjmzxfzhl.common.log.annotation.Log;
import com.zjmzxfzhl.modules.flowable.common.BaseFlowableController;
import com.zjmzxfzhl.modules.flowable.service.FlowableTaskService;
import com.zjmzxfzhl.modules.flowable.vo.IdentityRequest;
import org.flowable.identitylink.api.history.HistoricIdentityLink;
import org.flowable.task.api.history.HistoricTaskInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 庄金明
 * @date 2020年3月24日
 */
@RestController
@RequestMapping("/flowable/taskIdentityLink")
public class TaskIdentityLinkController extends BaseFlowableController {
    @Autowired
    protected FlowableTaskService flowableTaskService;

    @PreAuthorize("@elp.single('flowable:taskIdentityLink:list')")
    @GetMapping(value = "/list")
    public Result list(@RequestParam String taskId) {
        HistoricTaskInstance task = flowableTaskService.getHistoricTaskInstanceNotNull(taskId);
        List<HistoricIdentityLink> historicIdentityLinks = historyService.getHistoricIdentityLinksForTask(task.getId());
        return Result.ok(responseFactory.createTaskIdentityResponseList(historicIdentityLinks));
    }

    @Log(value = "新增任务授权")
    @PreAuthorize("@elp.single('flowable:taskIdentityLink:save')")
    @PostMapping(value = "/save")
    public Result save(@RequestBody IdentityRequest taskIdentityRequest) {
        flowableTaskService.saveTaskIdentityLink(taskIdentityRequest);
        return Result.ok();
    }

    @Log(value = "删除任务授权")
    @PreAuthorize("@elp.single('flowable:taskIdentityLink:delete')")
    @DeleteMapping(value = "/delete")
    public Result deleteIdentityLink(@RequestParam String taskId, @RequestParam String identityId,
            @RequestParam String identityType) {
        flowableTaskService.deleteTaskIdentityLink(taskId, identityId, identityType);
        return Result.ok();
    }
}
