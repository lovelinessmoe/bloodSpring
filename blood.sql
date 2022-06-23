/*
 Navicat Premium Data Transfer

 Source Server         : Local
 Source Server Type    : MySQL
 Source Server Version : 80028
 Source Host           : localhost:3306
 Source Schema         : blood

 Target Server Type    : MySQL
 Target Server Version : 80028
 File Encoding         : 65001

 Date: 23/06/2022 17:21:06
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for blood
-- ----------------------------
DROP TABLE IF EXISTS `blood`;
CREATE TABLE `blood` (
  `blood_id` char(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '主键',
  `in_time` datetime DEFAULT NULL COMMENT '入库时间',
  `in_source` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '血液来源',
  `in_person` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '献血者id',
  `blood_type` int NOT NULL COMMENT '血液种类BLOOD_TYPE (0普通冰冻血浆、1悬浮红细胞、2机采血小板)',
  `take_time` datetime DEFAULT NULL COMMENT '采血日期',
  `expire_time` datetime DEFAULT NULL COMMENT '过期日期',
  `blood_group` int NOT NULL COMMENT '血型BLOOD_GROUP(abo)',
  `rh` int NOT NULL COMMENT 'RH0阴性 1阳性',
  `take_person` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '采血人id',
  `blood_volume` int NOT NULL COMMENT '血量 cc',
  `state` int DEFAULT '0' COMMENT '血液状态 BLOOD_STATE(0入库未使用 1已使用 2过期弃用)',
  PRIMARY KEY (`blood_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='血液信息\n';

-- ----------------------------
-- Table structure for blood_trans_form
-- ----------------------------
DROP TABLE IF EXISTS `blood_trans_form`;
CREATE TABLE `blood_trans_form` (
  `form_id` char(32) COLLATE utf8mb4_bin NOT NULL COMMENT '输血申请单id',
  `need_person` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '患者id',
  `need_volume` int NOT NULL COMMENT '预订量cc',
  `need_blood_type` int NOT NULL COMMENT '血液种类BLOOD_TYPE (0普通冰冻血浆、1悬浮红细胞、2机采血小板)',
  `apply_user` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '申请医师id',
  `apply_time` datetime DEFAULT NULL COMMENT '申请时间',
  `state` int NOT NULL DEFAULT '0' COMMENT '申请单状态BLOOD_TRANS_FORM_STATE(0申请未处理 1通过 2拒绝)',
  `trans_bloods_id` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '输血申请成功的配型id',
  PRIMARY KEY (`form_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='输血申请单';

-- ----------------------------
-- Table structure for dict
-- ----------------------------
DROP TABLE IF EXISTS `dict`;
CREATE TABLE `dict` (
  `code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字典码',
  `dict_key` int NOT NULL COMMENT '字典值',
  `dict_value` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '字典名称',
  `remark` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '字典备注',
  PRIMARY KEY (`code`,`dict_key`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='字典表';

-- ----------------------------
-- Records of dict
-- ----------------------------
BEGIN;
INSERT INTO `dict` VALUES ('BLOOD_GROUP', 0, 'A', NULL);
INSERT INTO `dict` VALUES ('BLOOD_GROUP', 1, 'B', NULL);
INSERT INTO `dict` VALUES ('BLOOD_GROUP', 2, 'AB', NULL);
INSERT INTO `dict` VALUES ('BLOOD_GROUP', 3, 'O', NULL);
INSERT INTO `dict` VALUES ('BLOOD_STATE', 0, '入库未使用', NULL);
INSERT INTO `dict` VALUES ('BLOOD_STATE', 1, '已使用', NULL);
INSERT INTO `dict` VALUES ('BLOOD_STATE', 2, '过期弃用', NULL);
INSERT INTO `dict` VALUES ('BLOOD_TRANS_FORM_STATE', 0, '申请未处理', NULL);
INSERT INTO `dict` VALUES ('BLOOD_TRANS_FORM_STATE', 1, '通过', NULL);
INSERT INTO `dict` VALUES ('BLOOD_TRANS_FORM_STATE', 2, '拒绝', NULL);
INSERT INTO `dict` VALUES ('BLOOD_TYPE', 0, '普通冰冻血浆', NULL);
INSERT INTO `dict` VALUES ('BLOOD_TYPE', 1, '悬浮红细胞', NULL);
INSERT INTO `dict` VALUES ('BLOOD_TYPE', 2, '机采血小板', NULL);
INSERT INTO `dict` VALUES ('RH', 0, '阴性', NULL);
INSERT INTO `dict` VALUES ('RH', 1, '阳性', NULL);
INSERT INTO `dict` VALUES ('ROlE', 1, '管理员', NULL);
INSERT INTO `dict` VALUES ('ROlE', 2, '献血者/患者', NULL);
INSERT INTO `dict` VALUES ('ROlE', 3, '医生', NULL);
INSERT INTO `dict` VALUES ('USER_SEX', 0, '女', NULL);
INSERT INTO `dict` VALUES ('USER_SEX', 1, '男', NULL);
COMMIT;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `role_id` int NOT NULL AUTO_INCREMENT COMMENT '用户角色',
  `role_name` char(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '角色名称',
  `role_name_en` char(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '角色名称EN',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='角色';

-- ----------------------------
-- Records of role
-- ----------------------------
BEGIN;
INSERT INTO `role` VALUES (1, '管理员', 'ADMIN');
INSERT INTO `role` VALUES (2, '献血者/患者', 'USER');
INSERT INTO `role` VALUES (3, '医生', 'DOCTOR');
COMMIT;

-- ----------------------------
-- Table structure for trans_bloods
-- ----------------------------
DROP TABLE IF EXISTS `trans_bloods`;
CREATE TABLE `trans_bloods` (
  `trans_bloods_id` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '血液组ID',
  `blood_id` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '血液ID',
  PRIMARY KEY (`trans_bloods_id`,`blood_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='配型的血液组';

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` char(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '主键',
  `role_id` int NOT NULL DEFAULT '2' COMMENT '角色id',
  `user_name` char(16) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '昵称',
  `password` char(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '登录密码，加密后保存',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '邮箱',
  `real_name` char(16) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '姓名',
  `sex` tinyint DEFAULT NULL COMMENT '性别USER_SEX 0女 1男',
  `age` int DEFAULT NULL COMMENT '年龄',
  `blood_group` int DEFAULT NULL COMMENT '血型BLOOD_GROUP(abo)',
  `rh` int DEFAULT NULL COMMENT 'RH0阴性 1阳性',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='用户大表,管理员和医生都是这张';

SET FOREIGN_KEY_CHECKS = 1;
