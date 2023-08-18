package com.yzl.service.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 设备表
 *
 * @author kai
 * @date 2023/08/18 5:28 下午
 */
@Data
@TableName("tb_equipment")
@EqualsAndHashCode(callSuper = true)
public class Equipment extends CommonFields {

    /**
     * 设备ID
     */
    @TableId
    private String equipmentId;
    /**
     * 设备名称
     */
    private String equipmentName;
    /**
     * 设备图片
     */
    private String equipmentImage;
    /**
     * 设备SN码
     */
    private String equipmentSnCode;
    /**
     * 设备状态
     */
    private String equipmentStatus;
    /**
     * 设备协议
     */
    private String equipmentProtocol;
}
