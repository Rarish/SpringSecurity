package com.example.potic.rbac.controller;


import com.example.potic.rbac.entity.SysUserRole;
import com.example.potic.rbac.service.impl.SysUserRoleServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhouzuyi
 * @since 2021-02-05
 */
@RestController
@RequestMapping("/rbac/sys-user-role")
public class SysUserRoleController extends BaseController<SysUserRoleServiceImpl, SysUserRole> {

}

