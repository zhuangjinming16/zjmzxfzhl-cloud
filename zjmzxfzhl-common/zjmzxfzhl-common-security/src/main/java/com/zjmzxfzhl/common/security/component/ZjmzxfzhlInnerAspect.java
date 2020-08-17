/*
 *
 *  *  Copyright (c) 2019-2020, 冷冷 (wangiegie@gmail.com).
 *  *  <p>
 *  *  Licensed under the GNU Lesser General Public License 3.0 (the "License");
 *  *  you may not use this file except in compliance with the License.
 *  *  You may obtain a copy of the License at
 *  *  <p>
 *  * https://www.gnu.org/licenses/lgpl.html
 *  *  <p>
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package com.zjmzxfzhl.common.security.component;

import com.zjmzxfzhl.common.core.constant.SecurityConstants;
import com.zjmzxfzhl.common.core.util.IpUtils;
import com.zjmzxfzhl.common.core.util.PasswordUtil;
import com.zjmzxfzhl.common.core.util.WebUtils;
import com.zjmzxfzhl.common.security.annotation.Inner;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author lengleng && 庄金明
 * @date 2019/02/14
 * <p>
 * 服务间接口鉴权处理逻辑
 */
@Slf4j
@Aspect
@AllArgsConstructor
public class ZjmzxfzhlInnerAspect implements Ordered {
    private final HttpServletRequest request;
    private final DiscoveryClient discoveryClient;
    private final JdbcClientDetailsService redisClientDetailsService;

    @SneakyThrows
    @Around("@annotation(inner)")
    public Object around(ProceedingJoinPoint point, Inner inner) {
        String innerValue = request.getHeader(SecurityConstants.INNER);
        if (SecurityConstants.INNER_TRUE.equals(innerValue)) {
            String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
            String[] clientInfo =
                    WebUtils.getClientInfo(authorization.replace(SecurityConstants.X_ZJMZXFZHL_APPLICATION_TOKEN_TYPE
                            , ""));
            String clientId = clientInfo[0];
            String clientSecret = clientInfo[1];
            ClientDetails clientDetails = redisClientDetailsService.loadClientByClientId(clientId);
            if (!PasswordUtil.matchesPassword(clientSecret, clientDetails.getClientSecret())) {
                log.warn("访问接口 {} 没有权限", point.getSignature().getName());
                throw new AccessDeniedException("Access is denied");
            }
            String ipAddr = IpUtils.getIpAddr(request);
            List<ServiceInstance> serviceInstances = discoveryClient.getInstances(clientId);
            boolean isServiceInstance =
                    serviceInstances.stream().anyMatch(serviceInstance -> ipAddr.equals(serviceInstance.getHost()));
            if (!isServiceInstance) {
                log.warn("访问接口 {} 没有权限", point.getSignature().getName());
                throw new AccessDeniedException("Access is denied");
            }
        }
        return point.proceed();
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE + 1;
    }
}
