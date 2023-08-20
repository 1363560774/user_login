package com.yzl.service.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 登录表
 *
 * @author kai
 * @date 2023/07/19 5:28 下午
 */
@Data
@TableName("tb_login_log")
@EqualsAndHashCode(callSuper = true)
public class LoginLog extends CommonFields {

    /**
     * logId
     */
    @TableId
    private String logId;

    /**
     * 微信唯一ID
     */
    private String unionId;
    /**
     * 用户编号
     */
    private String loginId;
}
