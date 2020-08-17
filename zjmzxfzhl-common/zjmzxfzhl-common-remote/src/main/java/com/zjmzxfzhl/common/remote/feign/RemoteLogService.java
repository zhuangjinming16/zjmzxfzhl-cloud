package com.zjmzxfzhl.common.remote.feign;

import com.zjmzxfzhl.common.core.Result;
import com.zjmzxfzhl.common.core.constant.SecurityConstants;
import com.zjmzxfzhl.common.core.constant.ServiceNameConstants;
import com.zjmzxfzhl.common.remote.feign.factory.RemoteLogServiceFallbackFactory;
import com.zjmzxfzhl.modules.sys.entity.SysLog;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * @author 庄金明
 * @date
 */
@FeignClient(path = "sys/log", contextId = "remoteLogService", value = ServiceNameConstants.SYS_SERVICE,
        fallbackFactory = RemoteLogServiceFallbackFactory.class)
public interface RemoteLogService {

    /**
     * 保存日志
     *
     * @param sysLog
     * @return
     */
    @PostMapping("/save")
    Result save(@RequestBody SysLog sysLog, @RequestHeader(SecurityConstants.INNER) String inner);

}
