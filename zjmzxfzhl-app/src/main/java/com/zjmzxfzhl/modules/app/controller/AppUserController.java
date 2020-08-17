package com.zjmzxfzhl.modules.app.controller;

import com.zjmzxfzhl.common.core.Result;
import com.zjmzxfzhl.common.core.base.BaseController;
import com.zjmzxfzhl.common.core.base.UserInfo;
import com.zjmzxfzhl.common.security.annotation.Inner;
import com.zjmzxfzhl.modules.app.entity.AppUser;
import com.zjmzxfzhl.modules.app.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户Controller
 *
 * @author 庄金明
 */
@RestController
@RequestMapping("/app/user")
public class AppUserController extends BaseController {

    @Autowired
    private AppUserService appUserService;

    @Inner
    @GetMapping("/info")
    public Result info(@RequestParam("userId") String userId) {
        AppUser appUser = appUserService.getById(userId);
        UserInfo userInfo = new UserInfo(appUser.getUserId(), appUser.getUserName(), appUser.getPassword(), null,
                null, null, AuthorityUtils.NO_AUTHORITIES);
        return Result.ok(userInfo);
    }
}
