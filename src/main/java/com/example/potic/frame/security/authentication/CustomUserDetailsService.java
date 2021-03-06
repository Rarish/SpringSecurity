package com.example.potic.frame.security.authentication;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.potic.frame.exception.CustomException;
import com.example.potic.frame.security.rbac.entity.SysUser;
import com.example.potic.frame.security.rbac.service.SysRoleService;
import com.example.potic.frame.security.rbac.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private SysUserService userService;
    @Autowired
    private SysRoleService roleService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = userService.getOne(new QueryWrapper<SysUser>().eq("username",username));
        if(sysUser == null){
            throw new UsernameNotFoundException("用户名未找到");
        }
        String password = passwordEncoder.encode(sysUser.getPassword());
        //基于role进行控制
        Set<GrantedAuthority> authorities = new HashSet<>();
        for(String role : roleService.getResourcesByUID(sysUser.getId())){
            authorities.add(new SimpleGrantedAuthority(role));
        }
        User user = new User(username,password,authorities);
        return user;
    }
}
