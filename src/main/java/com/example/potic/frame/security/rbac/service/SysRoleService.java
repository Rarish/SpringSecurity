package com.example.potic.frame.security.rbac.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.potic.frame.security.rbac.entity.SysRole;

import java.util.Set;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhouzuyi
 * @since 2021-02-05
 */
public interface SysRoleService extends IService<SysRole> {
    Set<String> getResourcesByUID(Integer uid);
}
