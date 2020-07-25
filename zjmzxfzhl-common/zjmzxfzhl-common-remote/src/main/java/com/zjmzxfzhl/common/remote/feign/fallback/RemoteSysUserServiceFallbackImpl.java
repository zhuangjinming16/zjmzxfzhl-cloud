package com.zjmzxfzhl.common.remote.feign.fallback;

import com.zjmzxfzhl.common.remote.feign.RemoteSysUserService;
import com.zjmzxfzhl.common.remote.feign.base.RemoteUserInfoServiceFallbackImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author 庄金明
 * @date
 */
@Slf4j
@Component
public class RemoteSysUserServiceFallbackImpl extends RemoteUserInfoServiceFallbackImpl implements RemoteSysUserService {
}
