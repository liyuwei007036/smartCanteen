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

 Date: 25/03/2020 21:02:32
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键Id',
  `name` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '名称',
  `parent_id` bigint(20) NULL DEFAULT NULL COMMENT '上级id',
  `code` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '编码',
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '路径',
  `level` bigint(255) NULL DEFAULT NULL COMMENT '级别',
  `has_children` bit(1) NULL DEFAULT NULL COMMENT '是否拥有子节点',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 44 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '组织' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES (11, '查询', 26, 'role:list', '25-26-', 3, b'0');
INSERT INTO `permission` VALUES (12, '查询', 16, 'origination:view', '15-16-', 3, b'0');
INSERT INTO `permission` VALUES (13, '查询', 34, 'employee:view', '15-34-', 3, b'0');
INSERT INTO `permission` VALUES (15, '人员管理', 0, 'employee', '-', 1, b'1');
INSERT INTO `permission` VALUES (16, '组织管理', 15, 'originationManagement', '15-', 2, b'1');
INSERT INTO `permission` VALUES (17, '新增', 16, 'origination:add', '15-16-', 3, b'0');
INSERT INTO `permission` VALUES (18, '编辑', 16, 'origination:update', '15-16-', 3, b'0');
INSERT INTO `permission` VALUES (19, '删除', 16, 'origination:deleted', '15-16-', 3, b'0');
INSERT INTO `permission` VALUES (20, '卡片管理', 0, 'icCard:list', '-', 1, b'1');
INSERT INTO `permission` VALUES (21, '充值补扣', 20, 'icCard', '20-', 2, b'1');
INSERT INTO `permission` VALUES (22, '充值', 21, 'recharge:recharge', '20-21-', 3, b'0');
INSERT INTO `permission` VALUES (23, '挂失', 39, 'icCard:loss', '20-39-', 3, b'0');
INSERT INTO `permission` VALUES (24, '补卡', 39, 'icCard:patch', '20-39-', 3, b'0');
INSERT INTO `permission` VALUES (25, '角色管理', 0, 'roleManagement', '-', 1, b'1');
INSERT INTO `permission` VALUES (26, '角色列表', 25, 'role', '25-', 2, b'1');
INSERT INTO `permission` VALUES (27, '新增', 26, 'role:add', '25-26-', 3, b'0');
INSERT INTO `permission` VALUES (28, '编辑', 26, 'role:update', '25-26-', 3, b'0');
INSERT INTO `permission` VALUES (29, '删除', 26, 'role:deleted', '25-26-', 3, b'0');
INSERT INTO `permission` VALUES (30, '角色授权', 25, 'role:authorization', '25-', 2, b'0');
INSERT INTO `permission` VALUES (31, '消费管理', 0, 'order', '-', 1, b'1');
INSERT INTO `permission` VALUES (32, '充值记录', 31, 'recharge:listlog', '31-', 2, b'0');
INSERT INTO `permission` VALUES (33, '消费记录', 31, 'order:list', '31-', 2, b'0');
INSERT INTO `permission` VALUES (34, '人员资料', 15, 'employee', '15-', 2, b'1');
INSERT INTO `permission` VALUES (35, '新增', 34, 'employee:add', '15-34-', 3, b'0');
INSERT INTO `permission` VALUES (36, '编辑', 34, 'employee:update', '15-34-', 3, b'0');
INSERT INTO `permission` VALUES (37, '销户', 34, 'icCard:deleted', '15-34-', 3, b'0');
INSERT INTO `permission` VALUES (38, '补扣', 21, 'icCard:deduction', '20-21-', 3, b'0');
INSERT INTO `permission` VALUES (39, '遗失补办', 20, 'icCard2', '20-', 2, b'1');
INSERT INTO `permission` VALUES (40, '日志管理', 0, 'log', '-', 1, b'1');
INSERT INTO `permission` VALUES (41, '操作日志', 40, 'log:operation', '40-', 2, b'0');
INSERT INTO `permission` VALUES (42, '安全日志', 40, 'log:login', '40-', 2, b'0');
INSERT INTO `permission` VALUES (43, '卡机管理', 0, 'machine:view', '-', 1, b'1');
INSERT INTO `permission` VALUES (44, '新增', 43, 'machine:add', '43-', 2, b'0');
INSERT INTO `permission` VALUES (45, '删除', 43, 'machine:delete', '43-', 2, b'0');
INSERT INTO `permission` VALUES (46, '编辑', 43, 'machine:update', '43-', 2, b'0');
INSERT INTO `permission` VALUES (47, '挂失', 39, 'icCard:unloss', '20-39-', 3, b'0');


SET FOREIGN_KEY_CHECKS = 1;
