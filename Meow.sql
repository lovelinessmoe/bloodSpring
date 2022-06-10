/*
 Navicat Premium Data Transfer

 Source Server         : Meow
 Source Server Type    : MySQL
 Source Server Version : 80028
 Source Host           : localhost:3306
 Source Schema         : Meow

 Target Server Type    : MySQL
 Target Server Version : 80028
 File Encoding         : 65001

 Date: 04/04/2022 18:38:03
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article`
(
    `article_id`     char(32) CHARACTER SET utf8 COLLATE utf8_general_ci     NOT NULL COMMENT '文章id/英文',
    `article_title`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文章名称',
    `create_time`    datetime                                                NOT NULL COMMENT '发布时间',
    `article_short`  char(64) CHARACTER SET utf8 COLLATE utf8_general_ci     NOT NULL COMMENT '文章简介',
    `views_count`    int          DEFAULT '0' COMMENT '查看人数',
    `comments_count` int          DEFAULT '0' COMMENT '评论人数',
    `is_top`         tinyint      DEFAULT '0' COMMENT '是否是置顶',
    `img_url`        varchar(255) DEFAULT NULL COMMENT '文章图片url',
    PRIMARY KEY (`article_id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb3
  ROW_FORMAT = COMPACT;

-- ----------------------------
-- Table structure for article_detail
-- ----------------------------
DROP TABLE IF EXISTS `article_detail`;
CREATE TABLE `article_detail`
(
    `article_content` varchar(1500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '文章的内容',
    `article_id`      char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '文章的id/EN',
    PRIMARY KEY (`article_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`
(
    `comment_id` char(16) COLLATE utf8_bin NOT NULL,
    `user_id`    char(32) CHARACTER SET utf8 COLLATE utf8_bin           DEFAULT NULL COMMENT '评论人',
    `content`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '评论内容',
    `type`       tinyint(1)                                             DEFAULT NULL COMMENT '0h5 1md',
    `creat_time` date                                                   DEFAULT NULL COMMENT '创建时间',
    `p_id`       int                                                    DEFAULT NULL COMMENT '父id',
    `level`      varchar(255) COLLATE utf8_bin                          DEFAULT NULL COMMENT '层级',
    `article_id` char(32) CHARACTER SET utf8 COLLATE utf8_bin           DEFAULT NULL COMMENT '文章id',
    PRIMARY KEY (`comment_id`) USING BTREE,
    KEY `comment` (`user_id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb3
  COLLATE = utf8_bin
  ROW_FORMAT = COMPACT COMMENT ='文章评论';

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`
(
    `role_id`      int NOT NULL AUTO_INCREMENT COMMENT '用户角色',
    `role_name`    char(16) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '角色名称',
    `role_name_en` char(16) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '角色名称EN',
    PRIMARY KEY (`role_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`
(
    `user_id`   char(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '主键',
    `role_id`   int                                          DEFAULT '0' COMMENT '角色id',
    `user_name` char(16) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '昵称',
    `telephone` char(11) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '电话号',
    `age`       int                                          DEFAULT NULL COMMENT '年龄',
    `password`  char(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '登录密码，加密为md5格式后保存',
    `email`     varchar(255) COLLATE utf8_bin                DEFAULT NULL COMMENT '邮箱',
    PRIMARY KEY (`user_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb3
  COLLATE = utf8_bin
  ROW_FORMAT = COMPACT COMMENT ='用户 \n';

SET FOREIGN_KEY_CHECKS = 1;
