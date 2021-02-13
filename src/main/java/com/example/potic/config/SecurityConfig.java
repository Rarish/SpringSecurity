package com.example.potic.config;

import com.example.potic.security.authentication.CustomUserDetailsService;
import com.example.potic.security.authorization.AuthorizeSecurityInterceptor;
import com.example.potic.security.authentication.LoginAuthenticationProvider;
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
                .logout().permitAll()
            .and()
                .httpBasic()
            .and()
                .formLogin()
                .loginProcessingUrl("/loginSys")
                .permitAll()//支持表单登录
            .and()
                .csrf().disable()
            .addFilterAt(new AuthorizeSecurityInterceptor(), FilterSecurityInterceptor.class);
    }
}
