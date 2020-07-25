package com.zjmzxfzhl.common.remote.feign.base;

import com.zjmzxfzhl.common.core.Result;
import com.zjmzxfzhl.common.core.base.UserInfo;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 庄金明
 * @date
 */
@Slf4j
public class RemoteUserInfoServiceFallbackImpl implements RemoteUserInfoService {

    @Setter
    protected Throwable cause;

    /**
     * 通过用户名查询用户信息
     *
     * @param userId 用户名
     * @return Result
     */
    @Override
    public Result<UserInfo> info(@RequestParam("userId") String userId) {
        log.error("feign 查询用户信息失败:{}", userId, cause);
        return null;
    }

}
