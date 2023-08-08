package com.yzl.service.common;

/**
 * 注释
 *
 * @author kai
 * @date 2023/07/19 5:28 下午
 */
public interface Constant {

    String ITEM_TYPE_SINGLE = "SINGLE";
    String ITEM_TYPE_MULTIPLE = "MULTIPLE";
    String ITEM_TYPE_JUDGE = "JUDGE";

    String ITEM_DIFFICULTY_SIMPLE = "SIMPLE";
    String ITEM_DIFFICULTY_GENERAL = "GENERAL";
    String ITEM_DIFFICULTY_DIFFICULT = "DIFFICULT";

    String ERROR_ITEM_EXCEL = "ERROR_ITEM_EXCEL";
    String ERROR_USER_EXCEL = "ERROR_USER_EXCEL";

    String GROUP_VOLUME_KEY = "GROUP_VOLUME_KEY_";
    String GROUP_VOLUME_ITEM_KEY = "GROUP_VOLUME_ITEM_KEY_";
    String GROUP_VOLUME_TMP_ITEM_KEY = "GROUP_VOLUME_TMP_ITEM_KEY_";

    String PAPER_VERSION_REDIS = "PAPER_VERSION_REDIS_";

    String EXAM_STATUS_START = "START";
    String EXAM_STATUS_NOT_START = "NOT_START";
    String EXAM_STATUS_DRAFT = "DRAFT";
    String EXAM_STATUS_PUBLISH = "PUBLISH";
    String EXAM_STATUS_INVALID = "INVALID";
    String EXAM_STATUS_END = "END";
    String EXAM_STATUS_DELETE = "DELETE";

    String EXAM_RELATED_TYPE_USER = "USER";
    String EXAM_RELATED_TYPE_ORGANIZATION = "ORGANIZATION";

    Integer ZERO = 0;
    Long ONE_DAY = 86400L;

    String ASC = "ASC";
    String DESC = "DESC";
    Integer LIMIT = 10;
    String SUCCESS = "success";


    //redis缓存key===============================================================
    //试卷试题缓存用户答题信息缓存
    String EXAM_USER_PAPER_ITEM_REDIS = "EXAM_USER_PAPER_ITEM_REDIS_";
    //考试时间信息缓存
    String EXAM_USER_PAPER_TIME_REDIS = "EXAM_USER_PAPER_TIME_REDIS_";
    String PASS = "PASS";
    String NOT_PASS = "NOT_PASS";
}
