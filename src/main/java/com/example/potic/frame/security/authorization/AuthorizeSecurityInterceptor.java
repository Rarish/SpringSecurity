package com.example.potic.frame.security.authorization;

import com.example.potic.common.utils.SpringUtils;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

import javax.annotation.PostConstruct;
import javax.servlet.*;
import java.io.IOException;


/**
 * 资源访问过滤器
 * <p>
 * 默认的过滤器是{@link FilterSecurityInterceptor}
 * @author ygsama
 */
public class AuthorizeSecurityInterceptor extends AbstractSecurityInterceptor implements Filter {
    @Override
    public void setAccessDecisionManager(AccessDecisionManager accessDecisionManager) {
        super.setAccessDecisionManager((AccessDecisionManager) SpringUtils.getBean("accessDecisionManagerImpl"));
    }

    @Override
    public Class<?> getSecureObjectClass() {
        return FilterInvocation.class;
    }

    @Override
    public SecurityMetadataSource obtainSecurityMetadataSource() {
        return (SecurityMetadataSource) SpringUtils.getBean("filterSecurityMetaDataResources");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        FilterInvocation filterInvocation = new FilterInvocation(servletRequest,servletResponse,filterChain);
        InterceptorStatusToken token = super.beforeInvocation(filterInvocation);
        try{
            filterInvocation.getChain().doFilter(filterInvocation.getRequest(),filterInvocation.getResponse());
        }finally {
            super.afterInvocation(token,null);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("----------AuthorizeSecurityInterceptor.init()");
    }

    @Override
    public void destroy() {
        System.out.println("----------AuthorizeSecurityInterceptor.destroy()");
    }
}
