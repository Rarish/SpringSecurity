package com.example.potic.frame.security.authorization;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.potic.frame.security.rbac.entity.SysResources;
import com.example.potic.frame.security.rbac.entity.SysRole;
import com.example.potic.frame.security.rbac.entity.SysRoleResources;
import com.example.potic.frame.security.rbac.service.SysResourcesService;
import com.example.potic.frame.security.rbac.service.SysRoleResourcesService;
import com.example.potic.frame.security.rbac.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * 根据当前请求的url,得到对应需要的角色
 * */
@Component("filterSecurityMetaDataResources")
public class FilterSecurityMetaDataResources implements FilterInvocationSecurityMetadataSource {
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysResourcesService sysResourcesService;
    @Autowired
    private SysRoleResourcesService sysRoleResourcesService;

    private static Map<String,Collection<ConfigAttribute>> resourceMap = null;


    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        FilterInvocation filterInvocation = (FilterInvocation) object;
        //获取请求url
        String requestUrl = filterInvocation.getRequestUrl();
        if(requestUrl.equalsIgnoreCase("/login")){
            return null;
        }
        //获取所有资源
        List<SysResources> resources =sysResourcesService.list();
        //请求资源对应的角色列表
        Set<ConfigAttribute> roles = new HashSet<>();

        Iterator<SysResources> iterator = resources.iterator();
        AntPathMatcher requestMatcher = new AntPathMatcher();
        while (iterator.hasNext()){
            SysResources resource = iterator.next();
            if(requestMatcher.match(resource.getUrl(),requestUrl)){
                for(SysRoleResources roleResources : sysRoleResourcesService.list(new QueryWrapper<SysRoleResources>().eq("resources_id",resource.getId()))){
                    roles.add(new SecurityConfig(sysRoleService.getById(roleResources.getRoleId()).getName()));
                }
            }
        }
        //若请求资源匹配不上角色,设为需登录角色
//        if(roles.isEmpty()){
//            return SecurityConfig.createList("ROLE_LOGIN");
//        }

        return roles;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return new ArrayList<>();
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
