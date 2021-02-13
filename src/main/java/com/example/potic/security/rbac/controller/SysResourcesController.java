package com.example.potic.security.rbac.controller;


import com.example.potic.base.BaseController;
import com.example.potic.security.rbac.entity.SysResources;
import com.example.potic.security.rbac.service.impl.SysResourcesServiceImpl;
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
@RequestMapping("/rbac/sys-resources")
public class SysResourcesController extends BaseController<SysResourcesServiceImpl, SysResources> {

}

