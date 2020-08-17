package com.zjmzxfzhl.common.remote.feign.base;

import com.zjmzxfzhl.common.core.Result;
import com.zjmzxfzhl.common.core.base.UserInfo;
import com.zjmzxfzhl.common.core.constant.SecurityConstants;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 庄金明
 * @date
 */
public interface RemoteUserInfoService {

    /**
     * 通过用户名查询用户信息
     *
     * @param userId 用户名
     * @return Result
     */
    @GetMapping("/user/info")
    Result<UserInfo> info(@RequestParam("userId") String userId, @RequestHeader(SecurityConstants.INNER) String inner);
}
