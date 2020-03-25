/*
 Navicat Premium Data Transfer

 Source Server         : mysql8.0
 Source Server Type    : MySQL
 Source Server Version : 80018
 Source Host           : 192.168.3.11:3306
 Source Schema         : smartcanteen

 Target Server Type    : MySQL
 Target Server Version : 80018
 File Encoding         : 65001

 Date: 25/03/2020 21:01:38
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for ic_card
-- ----------------------------
DROP TABLE IF EXISTS `ic_card`;
CREATE TABLE `ic_card`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键Id',
  `no` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '卡号',
  `employee_no` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '员工工号',
  `employee_name` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '员工姓名',
  `account_status` int(20) NULL DEFAULT NULL COMMENT '账户状态',
  `status` int(20) NULL DEFAULT NULL COMMENT '状态',
  `type` int(20) NULL DEFAULT NULL COMMENT '类型',
  `employee_id` bigint(20) NULL DEFAULT NULL COMMENT '员工id',
  `expense` double NULL DEFAULT NULL COMMENT '工本费',
  `deposit` double NULL DEFAULT NULL COMMENT '押金',
  `open_card_amount` double NULL DEFAULT NULL COMMENT '开卡金额',
  `current_balance` double NULL DEFAULT NULL COMMENT '当前余额',
  `minimum_balance` double NULL DEFAULT NULL COMMENT '最低余额',
  `validity_time` datetime(0) NULL DEFAULT NULL COMMENT '有效期',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '密码',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `creator_id` bigint(255) NULL DEFAULT NULL COMMENT '创建人id',
  `creator_account` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人工号',
  `creator_name` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人姓名',
  `last_update_time` datetime(0) NULL DEFAULT NULL COMMENT '最近更新时间',
  `last_update_id` bigint(20) NULL DEFAULT NULL COMMENT '最近更新人id',
  `last_update_account` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '最近更新人工号',
  `last_update_name` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '最近更新人姓名',
  `version` bigint(255) NULL DEFAULT NULL COMMENT '逻辑锁',
  `deleted` bit(1) NULL DEFAULT NULL COMMENT '是否删除 0 1',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `ic_card_no`(`no`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = 'iC卡' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
