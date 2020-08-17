package com.zjmzxfzhl.common.security.service;

import com.zjmzxfzhl.common.core.Result;
import com.zjmzxfzhl.common.core.base.UserInfo;
import com.zjmzxfzhl.common.core.constant.SecurityConstants;
import com.zjmzxfzhl.common.core.security.SecurityUser;
import com.zjmzxfzhl.common.core.util.CommonUtil;
import com.zjmzxfzhl.common.core.util.SecurityUtils;
import com.zjmzxfzhl.common.core.util.SpringContextUtils;
import com.zjmzxfzhl.common.remote.feign.RemoteSysUserService;
import com.zjmzxfzhl.common.remote.feign.base.RemoteUserInfoService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.common.exceptions.InvalidRequestException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 用户验证处理
 *
 * @author 庄金明
 */
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private JdbcClientDetailsService redisClientDetailsService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String beanId = RemoteSysUserService.BEAN_ID, clientId = null;
        Authentication authentication = SecurityUtils.getAuthentication();
        if (authentication == null) {
            HttpServletRequest request =
                    ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            if (SecurityConstants.OAUTH_AUTHORIZE_URL.equals(request.getServletPath())) {
                clientId = request.getParameter(SecurityConstants.CLIENT_ID);
                if (clientId == null || clientId.isEmpty()) {
                    throw new InvalidRequestException("Missing client ID");
                }
            }
        } else {
            clientId = authentication.getName();
        }
        if (CommonUtil.isNotEmptyStr(clientId)) {
            ClientDetails clientDetails = redisClientDetailsService.loadClientByClientId(clientId);
            if (clientDetails != null && clientDetails.getAdditionalInformation() != null) {
                Object remoteUserInfoServiceBeanId =
                        clientDetails.getAdditionalInformation().get(SecurityConstants.REMOTE_USER_INFO_SERVICE);
                if (CommonUtil.isNotEmptyObject(remoteUserInfoServiceBeanId)) {
                    beanId = remoteUserInfoServiceBeanId.toString();
                }
            }
        }
        RemoteUserInfoService remoteUserInfoService = SpringContextUtils.getBean(beanId);
        Result<UserInfo> result = remoteUserInfoService.info(username, SecurityConstants.INNER_TRUE);
        return getUserDetails(result);
    }

    /**
     * 构建userdetails
     *
     * @param result 用户信息
     * @return
     */
    private UserDetails getUserDetails(Result<UserInfo> result) {
        if (result == null || result.getData() == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        UserInfo info = result.getData();
        // 构造security用户
        return new SecurityUser(info.getRoleId(), info.getOrgId(), info.getUserName(),
                info.getAdditionalInformation(), info.getUserId(), info.getPassword(), true, true, true, true,
                info.getAuthorities());
    }
}
