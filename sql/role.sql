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

 Date: 25/03/2020 21:02:57
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键Id',
  `name` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '名称',
  `can_edit` bit(1) NOT NULL COMMENT '是否能删除',
  `is_default` bit(1) NULL DEFAULT NULL COMMENT '默认',
  `can_select` bit(1) NULL DEFAULT NULL,
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
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '岗位' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, '系统管理员', b'0', NULL, b'0', '2020-03-04 21:17:48', 0, 'admin', '系统管理员', '2020-03-11 00:16:54', 1, 'admin', '系统管理员', 1, b'0');
INSERT INTO `role` VALUES (2, '普通员工', b'0', b'1', b'1', '2020-03-07 23:29:30', 1, 'admin', '系统管理员', '2020-03-07 23:29:30', 1, 'admin', '系统管理员', 0, b'0');


SET FOREIGN_KEY_CHECKS = 1;
