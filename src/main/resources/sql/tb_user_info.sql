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

 Date: 20/07/2023 16:52:58
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_user_info
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_info`;
CREATE TABLE `tb_user_info` (
  `user_id` bigint NOT NULL COMMENT '用户id',
  `username` varchar(25) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '用户名',
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '头像路径',
  `sex` bit(1) DEFAULT NULL COMMENT '性别',
  `age` tinyint unsigned DEFAULT NULL COMMENT '年龄',
  `email` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '邮箱',
  `phone` bigint DEFAULT NULL COMMENT '手机号',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '地址',
  `user_status` bit(1) DEFAULT NULL COMMENT '用户状态（0->创建，1->激活）',
  `create_time` datetime DEFAULT NULL COMMENT '注册时间',
  `introduction` varchar(1000) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '个人简介',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin;

SET FOREIGN_KEY_CHECKS = 1;
