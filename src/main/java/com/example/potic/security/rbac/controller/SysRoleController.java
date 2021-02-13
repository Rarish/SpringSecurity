package com.example.potic.security.rbac.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.potic.base.BaseController;
import com.example.potic.security.rbac.entity.SysRole;
import com.example.potic.security.rbac.entity.SysRoleResources;
import com.example.potic.security.rbac.service.SysRoleResourcesService;
import com.example.potic.security.rbac.service.impl.SysRoleServiceImpl;
import com.example.potic.result.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhouzuyi
 * @since 2021-02-05
 */
@RestController
@RequestMapping("/rbac/sys-role")
public class SysRoleController extends BaseController<SysRoleServiceImpl, SysRole> {
    @Autowired
    private SysRoleResourcesService roleResourcesService;

    @Override
    public ResponseEntity deleteByIds(List<Integer> ids) {
        for(Integer id : ids){
            roleResourcesService.remove(new QueryWrapper<SysRoleResources>().eq("roleId",id));
        }
        return super.deleteByIds(ids);
    }
}

