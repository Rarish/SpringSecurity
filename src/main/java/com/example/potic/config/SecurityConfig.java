package com.example.potic.config;

import com.example.potic.security.authentication.*;
import com.example.potic.security.authorization.AuthorizeSecurityInterceptor;
import com.example.potic.security.authorization.CustomAccessDeniedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private LoginAuthenticationProvider loginAuthenticationProvider;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    @Autowired
    private LoginSuccessHandler loginSuccessHandler;
    @Autowired
    private LoginFailureHandler loginFailureHandler;
    @Autowired
    private LoginOutSuccessHandler loginOutSuccessHandler;
    @Autowired
    private CustomAccessDeniedHandler customAccessDeniedHandler;

    //要求必须有一个这个实列
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    //这个方法进行登录认证
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(loginAuthenticationProvider).userDetailsService(customUserDetailsService);
    }

    //这个方法负责鉴权
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/login","/signIn").permitAll() //这两个url允许任意访问
                .anyRequest().authenticated()  //其余所有皆需要认证
            .and()
                .logout()
                .logoutSuccessHandler(loginOutSuccessHandler)
                .permitAll()
            .and()
                .httpBasic()
            .and()
                .formLogin()
                .loginProcessingUrl("/login") //用于指定前后端分离的时候调用后台登录接口的名称
                .successHandler(loginSuccessHandler)
                .failureHandler(loginFailureHandler)
            .and()
                .exceptionHandling().accessDeniedHandler(customAccessDeniedHandler) //配置无授权的处理器
            .and()
                .csrf().disable()
            .addFilterAt(new AuthorizeSecurityInterceptor(), FilterSecurityInterceptor.class);
    }
}
