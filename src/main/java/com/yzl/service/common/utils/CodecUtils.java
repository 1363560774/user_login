package com.yzl.service.common.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * 注释
 *
 * @author kai
 * @date 2023/11/21 18:24
 */
public class CodecUtils {

    public static String md5Hex(String data,String salt) {
        if (StringUtils.isBlank(salt)) {
            salt = data.hashCode() + "";
        }
        return DigestUtils.md5Hex(salt + DigestUtils.md5Hex(data));
    }

    public static String shaHex(String data, String salt) {
        if (StringUtils.isBlank(salt)) {
            salt = data.hashCode() + "";
        }
        return DigestUtils.sha512Hex(salt + DigestUtils.sha512Hex(data));
    }
}
