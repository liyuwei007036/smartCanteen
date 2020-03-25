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

 Date: 25/03/2020 21:02:24
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for origination
-- ----------------------------
DROP TABLE IF EXISTS `origination`;
CREATE TABLE `origination`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键Id',
  `name` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '名称',
  `parent_id` bigint(20) NULL DEFAULT NULL COMMENT '上级部门id',
  `code` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '编码',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '路径',
  `level` bigint(255) NULL DEFAULT NULL COMMENT '级别',
  `has_children` bit(1) NULL DEFAULT NULL COMMENT '是否拥有子节点',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `creator_id` bigint(255) NULL DEFAULT NULL COMMENT '创建人id',
  `creator_account` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人账号',
  `creator_name` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人姓名',
  `last_update_time` datetime(0) NULL DEFAULT NULL COMMENT '最近更新时间',
  `last_update_id` bigint(20) NULL DEFAULT NULL COMMENT '最近更新人id',
  `last_update_account` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '最近更新人工号',
  `last_update_name` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '最近更新人姓名',
  `version` bigint(255) NULL DEFAULT NULL COMMENT '逻辑锁',
  `deleted` bit(1) NULL DEFAULT NULL COMMENT '是否删除 0 1',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '组织' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
