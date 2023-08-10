/*
 Navicat Premium Data Transfer

 Source Server         : 
 Source Server Type    : MySQL
 Source Server Version : 50718
 Source Host           : 
 Source Schema         : kk_auto_generator

 Target Server Type    : MySQL
 Target Server Version : 50718
 File Encoding         : 65001

 Date: 10/08/2023 15:59:59
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for code_template
-- ----------------------------
DROP TABLE IF EXISTS `code_template`;
CREATE TABLE `code_template`  (
  `template_id` int(50) NOT NULL AUTO_INCREMENT COMMENT '模板编号',
  `template_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '模板类型【entity、mapper、service、controller、serviceimpl、mapperxml、po、dto、detailsvo、listvo、addvo、editvo、deletevo、listvviewue、apijs、routerjs】',
  `template_class` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '模板分类【sys：系统，per：个人】',
  `template_content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '模板内容',
  `template_file_url` varchar(4000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '模板文件地址',
  `create_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `template_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '模板名称',
  `template_language` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '模板语言【ftl、btl、vm】',
  PRIMARY KEY (`template_id`) USING BTREE,
  INDEX `idx_template_type`(`template_type`) USING BTREE,
  INDEX `idx_create_id`(`create_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '代码模板' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for code_template_group
-- ----------------------------
DROP TABLE IF EXISTS `code_template_group`;
CREATE TABLE `code_template_group`  (
  `template_group_id` int(50) NOT NULL AUTO_INCREMENT COMMENT '模板分组编号',
  `template_group_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '模板分组名称',
  `template_group_class` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '模板分组分类【sys：系统，per：个人】',
  `create_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '模板分组创建人',
  `template_language` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '模板语言【ftl、btl、vm】',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`template_group_id`) USING BTREE,
  INDEX `idx_create_id`(`create_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '代码模板分组' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for code_template_group_sub
-- ----------------------------
DROP TABLE IF EXISTS `code_template_group_sub`;
CREATE TABLE `code_template_group_sub`  (
  `template_group_id` int(50) NOT NULL COMMENT '模板分组编号',
  `template_id` int(50) NOT NULL COMMENT '模板编号',
  `template_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '模板类型',
  PRIMARY KEY (`template_group_id`, `template_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '代码模板分组明细' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for db_connect
-- ----------------------------
DROP TABLE IF EXISTS `db_connect`;
CREATE TABLE `db_connect`  (
  `db_connect_code` int(50) NOT NULL AUTO_INCREMENT COMMENT 'db链接编号',
  `db_connect_url` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'db链接地址',
  `db_connect_name` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'db链接名称',
  `db_connect_user` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'db链接用户',
  `db_connect_password` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'db链接密码',
  `db_connect_port` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'db链接端口',
  `db_connect_database` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'db链接数据库名',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `db_connect_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'db链接数据库类型【mysql/mssq】',
  PRIMARY KEY (`db_connect_code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '数据库链接' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
