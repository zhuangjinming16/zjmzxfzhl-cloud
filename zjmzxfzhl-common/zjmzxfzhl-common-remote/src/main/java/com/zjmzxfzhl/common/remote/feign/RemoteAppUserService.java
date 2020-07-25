package com.zjmzxfzhl.common.remote.feign;

import com.zjmzxfzhl.common.core.constant.ServiceNameConstants;
import com.zjmzxfzhl.common.remote.feign.base.RemoteUserInfoService;
import com.zjmzxfzhl.common.remote.feign.factory.RemoteSysUserServiceFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author 庄金明
 * @date
 */
@FeignClient(path = "app", contextId = RemoteAppUserService.BEAN_ID, qualifier = RemoteAppUserService.BEAN_ID, name =
        ServiceNameConstants.APP_SERVICE, fallbackFactory = RemoteSysUserServiceFallbackFactory.class)
public interface RemoteAppUserService extends RemoteUserInfoService {
    String BEAN_ID = "remoteAppUserService";
}
