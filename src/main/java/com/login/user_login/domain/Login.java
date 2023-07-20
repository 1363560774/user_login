package com.login.user_login.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 登录表
 *
 * @author kai
 * @date 2023/07/19 5:28 下午
 */
@Getter
@Setter
@ToString
@TableName("tb_login")
public class Login {

    /**
     * 用户编号
     */
    @TableId
    private Long loginId;

    /**
     * 登录名
     */
    private String loginName;

    /**
     * 密码
     */
    private String passwd;
}
