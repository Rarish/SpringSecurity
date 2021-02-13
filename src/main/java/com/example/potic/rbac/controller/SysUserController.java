package com.example.potic.rbac.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.potic.base.BaseController;
import com.example.potic.rbac.entity.SysUser;
import com.example.potic.rbac.entity.SysUserRole;
import com.example.potic.rbac.service.SysUserRoleService;
import com.example.potic.rbac.service.impl.SysUserServiceImpl;
import com.example.potic.result.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
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
@RequestMapping("/rbac/sys-user")
@Transactional
public class SysUserController extends BaseController<SysUserServiceImpl,SysUser> {
    @Autowired
    private SysUserRoleService userRoleService;

    @Override
    public ResponseEntity deleteByIds(List<Integer> ids) {
        for(Integer id : ids){
            userRoleService.remove(new QueryWrapper<SysUserRole>().eq("userId",id));
        }
        return super.deleteByIds(ids);
    }
}

