/*
 Navicat Premium Data Transfer

 Source Server         : kai
 Source Server Type    : MySQL
 Source Server Version : 80027
 Source Host           : kai.zhaokai96.com:13806
 Source Schema         : kai

 Target Server Type    : MySQL
 Target Server Version : 80027
 File Encoding         : 65001

 Date: 20/07/2023 16:20:28
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_login
-- ----------------------------
DROP TABLE IF EXISTS `tb_login`;
CREATE TABLE `tb_login` (
  `login_id` int NOT NULL COMMENT '唯一ID',
  `login_name` varchar(50) COLLATE armscii8_bin NOT NULL COMMENT '登录名',
  `passwd` varchar(128) COLLATE armscii8_bin NOT NULL COMMENT '密码',
  PRIMARY KEY (`login_id`)
) ENGINE=InnoDB DEFAULT CHARSET=armscii8 COLLATE=armscii8_bin;

SET FOREIGN_KEY_CHECKS = 1;
