package com.example.potic.frame.security.rbac.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.potic.frame.security.rbac.entity.SysRole;
import com.example.potic.frame.security.rbac.entity.SysUserRole;
import com.example.potic.frame.security.rbac.mapper.SysRoleMapper;
import com.example.potic.frame.security.rbac.service.SysRoleService;
import com.example.potic.frame.security.rbac.service.SysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhouzuyi
 * @since 2021-02-05
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {
    @Autowired
    private SysUserRoleService userRoleService;

    @Override
    public Set<String> getResourcesByUID(Integer uid) {
        Set<String> resources = new HashSet<>();
        for(SysUserRole userRole: userRoleService.list(new QueryWrapper<SysUserRole>().eq("user_id",uid))){
            resources.add(this.getById(userRole.getRoleId()).getName());
        }
        return resources;
    }
}
