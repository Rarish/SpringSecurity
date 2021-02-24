package com.example.potic.frame.security.rbac.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhouzuyi
 * @since 2021-02-05
 */
@Data
  @EqualsAndHashCode(callSuper = false)
  @Accessors(chain = true)
public class SysUser implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

    private String username;

      /**
     * 登录密码
     */
      private String password;

      /**
     * 昵称
     */
      private String nickname;

      /**
     * 手机号
     */
      private String mobile;

      /**
     * 邮箱地址
     */
      private String email;

      /**
     * QQ
     */
      private String qq;

      /**
     * 生日
     */
      private LocalDate birthday;

      /**
     * 性别
     */
      private Integer gender;

      /**
     * 头像地址
     */
      private String avatar;

      /**
     * 超级管理员、管理员、普通用户
     */
      private String userType;

      /**
     * 注册IP
     */
      private String regIp;

      /**
     * 最近登录IP
     */
      private String lastLoginIp;

      /**
     * 最近登录时间
     */
      private LocalDateTime lastLoginTime;

      /**
     * 登录次数
     */
      private Integer loginCount;

      /**
     * 用户备注
     */
      private String remark;

      /**
     * 用户状态
     */
      private Integer status;

      /**
     * 注册时间
     */
      private LocalDateTime createTime;

      /**
     * 更新时间
     */
      private LocalDateTime updateTime;


}
