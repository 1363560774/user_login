package com.login.user_login.common;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 注释
 *
 * @author kai
 * @date 2023/07/19 5:28 下午
 */
@Setter
@Getter
@ToString
public class Page<T> extends com.baomidou.mybatisplus.extension.plugins.pagination.Page {

    private String status;

    private String filter;
}
