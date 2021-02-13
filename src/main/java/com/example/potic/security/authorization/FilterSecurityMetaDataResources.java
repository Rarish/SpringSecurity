package com.example.potic.security.authorization;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.potic.security.rbac.entity.SysResources;
import com.example.potic.security.rbac.entity.SysRoleResources;
import com.example.potic.security.rbac.service.SysResourcesService;
import com.example.potic.security.rbac.service.SysRoleResourcesService;
import com.example.potic.security.rbac.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

@Component("filterSecurityMetaDataResources")
public class FilterSecurityMetaDataResources implements FilterInvocationSecurityMetadataSource {
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysResourcesService sysResourcesService;
    @Autowired
    private SysRoleResourcesService sysRoleResourcesService;

    private static Map<String,Collection<ConfigAttribute>> resourceMap = null;
    /**
     * 在Web服务器启动时，缓存系统中的所有权限映射。<br>
     * 被{@link PostConstruct}修饰的方法会在服务器加载Servlet的时候运行(构造器之后,init()之前) <br/>
     */
    @PostConstruct
    private void loadResources(){
        resourceMap = new HashMap();
        //获取数据库中的url资源与角色的匹配关系,url->角色(一对多)
        for(SysResources resource : sysResourcesService.list()){
            List<ConfigAttribute> configAttributes = new ArrayList<>();
            for(SysRoleResources roleResource : sysRoleResourcesService.list(new QueryWrapper<SysRoleResources>().eq("resources_id",resource.getId()))){
                configAttributes.add(new SecurityConfig(sysRoleService.getById(roleResource.getRoleId()).getName()));
            }
            resourceMap.put(resource.getUrl(),configAttributes);
        }
    }

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        FilterInvocation filterInvocation = (FilterInvocation) object;
        if(resourceMap == null){
            loadResources();
        }
        Iterator<String> it = resourceMap.keySet().iterator();
        while(it.hasNext()){
            String resURL = it.next();
            RequestMatcher requestMatcher = new AntPathRequestMatcher(resURL);
            if(requestMatcher.matches(filterInvocation.getHttpRequest())){
                return resourceMap.get(resURL);
            }
        }
        return null;
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
