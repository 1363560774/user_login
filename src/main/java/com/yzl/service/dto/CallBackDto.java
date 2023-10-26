package com.yzl.service.dto;

import lombok.Data;

@Data
public class CallBackDto {

    private String TaskId;
    private Integer Status;
    private String StatusStr;
    private String ResultUrl;
    private String ErrorMsg;
}
