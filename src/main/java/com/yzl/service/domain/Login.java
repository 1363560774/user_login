package com.yzl.service.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * 登录表
 *
 * @author kai
 * @date 2023/07/19 5:28 下午
 */
@Data
@TableName("tb_login")
@EqualsAndHashCode(callSuper = true)
public class Login extends CommonFields {

    /**
     * 用户编号
     */
    @TableId
    private String loginId;

    /**
     * 登录名
     */
    private String loginName;

    /**
     * 密码
     */
    private String passwd;
}
