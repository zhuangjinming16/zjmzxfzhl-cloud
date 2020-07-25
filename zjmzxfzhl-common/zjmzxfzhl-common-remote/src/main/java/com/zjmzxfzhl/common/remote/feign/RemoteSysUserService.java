package com.zjmzxfzhl.common.remote.feign;

import com.zjmzxfzhl.common.core.constant.ServiceNameConstants;
import com.zjmzxfzhl.common.remote.feign.base.RemoteUserInfoService;
import com.zjmzxfzhl.common.remote.feign.factory.RemoteSysUserServiceFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author 庄金明
 * @date
 */
@FeignClient(path = "sys", contextId = RemoteSysUserService.BEAN_ID, qualifier = RemoteSysUserService.BEAN_ID, name =
        ServiceNameConstants.SYS_SERVICE, fallbackFactory = RemoteSysUserServiceFallbackFactory.class)
public interface RemoteSysUserService extends RemoteUserInfoService {
    String BEAN_ID = "remoteSysUserService";
}
