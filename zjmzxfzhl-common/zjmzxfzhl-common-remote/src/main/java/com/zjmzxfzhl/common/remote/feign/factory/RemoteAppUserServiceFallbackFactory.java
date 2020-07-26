package com.zjmzxfzhl.common.remote.feign.factory;

import com.zjmzxfzhl.common.remote.feign.RemoteAppUserService;
import com.zjmzxfzhl.common.remote.feign.fallback.RemoteAppUserServiceFallbackImpl;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author 庄金明
 * @date
 */
@Component
public class RemoteAppUserServiceFallbackFactory implements FallbackFactory<RemoteAppUserService> {

    @Override
    public RemoteAppUserService create(Throwable throwable) {
        RemoteAppUserServiceFallbackImpl remoteUserServiceFallback = new RemoteAppUserServiceFallbackImpl();
        remoteUserServiceFallback.setCause(throwable);
        return remoteUserServiceFallback;
    }

}
