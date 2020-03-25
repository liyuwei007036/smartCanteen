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

 Date: 25/03/2020 21:02:44
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for recharge_log
-- ----------------------------
DROP TABLE IF EXISTS `recharge_log`;
CREATE TABLE `recharge_log`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键Id',
  `card_id` bigint(20) NOT NULL COMMENT '卡id',
  `money` double NOT NULL COMMENT '充值金额',
  `balance` double NOT NULL COMMENT '充值后金额',
  `type` int(10) NOT NULL COMMENT '充值类型',
  `employee_no` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '员工工号',
  `employee_name` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '员工姓名',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '描述',
  `card_no` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '卡号',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `creator_id` bigint(20) NULL DEFAULT NULL COMMENT '创建人id',
  `creator_account` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人',
  `creator_name` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人姓名',
  `last_update_time` datetime(0) NULL DEFAULT NULL COMMENT '最近更新时间',
  `last_update_id` bigint(20) NULL DEFAULT NULL COMMENT '最近更新人id',
  `last_update_account` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '最近更新人工号',
  `last_update_name` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '最近更新人姓名',
  `version` bigint(255) NULL DEFAULT NULL COMMENT '逻辑锁',
  `deleted` bit(1) NOT NULL COMMENT '是否删除 0 1',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '充值记录' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
