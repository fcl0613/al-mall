package com.al.almall.config;

import com.al.almall.entity.JwtToken;
import com.al.almall.entity.MallStoreOwner;
import com.al.almall.entity.MallUser;
import com.al.almall.service.SellerService;
import com.al.almall.utils.JwtUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;

@Slf4j
public class AccountRealm extends AuthorizingRealm {
    @Resource
    private JwtUtil jwtUtil;

    @Resource
    private SellerService sellerService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    /**
     * Shiro授权操作
     * 只有当需要检测用户权限的时候才会调用此方法，例如Controller层方法有Shiro权限注解
     * 使用userID去数据库中查找到对应的权限，然后将权限赋值给这个用户就可以实现权限的认证了
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.info("————权限认证 [ roles、permissions]————");
        //暂不编写，此处编写后，controller中可以使用@RequiresPermissions来对用户权限进行拦截
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        return authorizationInfo;
    }

    /**
     * Shiro 认证操作
     * 默认使用此方法进行用户名正确与否验证，错误抛出异常即可
     *
     * @param authenticationToken 就是从过滤器中传入的jwtToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        log.info("————————身份认证——————————");
        String token = (String) authenticationToken.getCredentials();
        if (null == token) {
            throw new AuthenticationException("无权限，请登录!");
        }
        // 解密获得username，用于和数据库进行对比
        String username = jwtUtil.getUsername(token);
        log.info("认证的username为"+username);
        if (null == username) {
            throw new AuthenticationException("用户认证失败，请重新登录");
        }
        // 从数据库中获取数据
        MallStoreOwner storeOwner = sellerService.getOne(new LambdaQueryWrapper<MallStoreOwner>()
                .eq(MallStoreOwner::getUsername, username));
        if (null == storeOwner) throw new AuthenticationException("用户不存在!");
        //交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，不配置的话则使用默认的SimpleCredentialsMatcher
        //用户名,凭证,realm name
        return new SimpleAuthenticationInfo(username,authenticationToken,"accountRealm");
    }
}
