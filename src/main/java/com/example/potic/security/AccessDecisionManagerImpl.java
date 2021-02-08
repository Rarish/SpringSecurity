package com.example.potic.security;

import com.example.potic.exception.CustomException;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Iterator;

@Component
public class AccessDecisionManagerImpl implements AccessDecisionManager {
    /**
     * 权限鉴定
     *
     * @param authentication   from SecurityContextHolder.getContext() =》 userDetails.getAuthorities()
     * @param object           是一个安全对象类型，FilterInvocation.class
     * @param configAttributes from MetaDataSource.getAttributes()，已经被框架做了非空判断
     */
    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        Iterator<ConfigAttribute> configAttributeIterator = configAttributes.iterator();
        while (configAttributeIterator.hasNext()){
            ConfigAttribute configAttribute = configAttributeIterator.next();
            String resourceRole = "ROLE_" + configAttribute.getAttribute();

            for(GrantedAuthority authority:authentication.getAuthorities()){
                //判断用户是否拥有要访问资源所需的角色
                if(resourceRole.trim().equalsIgnoreCase(authority.getAuthority().trim())){
                    return;
                }
            }
        }
        throw  new CustomException("权限不足");
    }

    /**
     * 被AbstractSecurityInterceptor调用，遍历ConfigAttribute集合，筛选出不支持的attribute
     */
    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    /**
     * 被AbstractSecurityInterceptor调用，验证AccessDecisionManager是否支持这个安全对象的类型。
     */
    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
