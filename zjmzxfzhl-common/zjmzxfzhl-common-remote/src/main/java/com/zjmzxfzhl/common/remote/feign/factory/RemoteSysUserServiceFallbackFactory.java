package com.zjmzxfzhl.common.remote.feign.factory;

import com.zjmzxfzhl.common.remote.feign.RemoteSysUserService;
import com.zjmzxfzhl.common.remote.feign.fallback.RemoteSysUserServiceFallbackImpl;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author 庄金明
 * @date
 */
@Component
public class RemoteSysUserServiceFallbackFactory implements FallbackFactory<RemoteSysUserService> {

    @Override
    public RemoteSysUserService create(Throwable throwable) {
        RemoteSysUserServiceFallbackImpl remoteUserServiceFallback = new RemoteSysUserServiceFallbackImpl();
        remoteUserServiceFallback.setCause(throwable);
        return remoteUserServiceFallback;
    }

}
