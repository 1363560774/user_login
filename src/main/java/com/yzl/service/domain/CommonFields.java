package com.yzl.service.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class CommonFields implements Serializable {
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 最后修改时间
     */
    private Date updateTime;
}
