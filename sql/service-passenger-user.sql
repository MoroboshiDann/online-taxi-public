/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80035
 Source Host           : localhost:3306
 Source Schema         : service-passenger-user

 Target Server Type    : MySQL
 Target Server Version : 80035
 File Encoding         : 65001

 Date: 23/03/2024 14:19:11
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for passenger_user
-- ----------------------------
DROP TABLE IF EXISTS `passenger_user`;
CREATE TABLE `passenger_user`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `gmt_create` datetime NULL DEFAULT NULL,
  `gmt_modified` datetime NULL DEFAULT NULL,
  `passenger_phone` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `passenger_name` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `passenger_gender` tinyint(1) NULL DEFAULT NULL COMMENT '0：未知，1：男，2：女',
  `state` tinyint(1) NULL DEFAULT NULL COMMENT '0是有效，1是失效',
  `profile_photo` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1770349562436624386 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of passenger_user
-- ----------------------------
INSERT INTO `passenger_user` VALUES (1770349562436624385, '2024-03-20 15:20:02', '2024-03-20 15:20:02', '15212311231', '张三', 0, 0, 'pic.msb.com');

SET FOREIGN_KEY_CHECKS = 1;
