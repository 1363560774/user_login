package com.yzl.service.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author kai
 * @date 2023/11/16 16:48
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ItemType {

    /**
     * 试题题型
     */
    SINGLE(1, "单选题"),
    MULTIPLE(2, "多选题"),
    JUDGE(3, "判断题"),
    FILL(4, "填空题"),
    READING(5, "阅读理解"),
    WRITING(6, "写作"),
    HEARING(7, "听力"),
    CONVERSION(8, "英汉互译"),
    COMMUNICATION(9, "口语交际"),
    ;
    private int code;

    private String name;
}
