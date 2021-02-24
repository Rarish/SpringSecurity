package com.example.potic.frame.security.rbac.controller;


import com.example.potic.common.base.BaseController;
import com.example.potic.frame.security.rbac.entity.SysRoleResources;
import com.example.potic.frame.security.rbac.service.impl.SysRoleResourcesServiceImpl;
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
@RequestMapping("/rbac/sys-role-resources")
public class SysRoleResourcesController extends BaseController<SysRoleResourcesServiceImpl, SysRoleResources> {

}

