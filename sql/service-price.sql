/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80035
 Source Host           : localhost:3306
 Source Schema         : service-price

 Target Server Type    : MySQL
 Target Server Version : 80035
 File Encoding         : 65001

 Date: 23/03/2024 14:19:20
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for price_rule
-- ----------------------------
DROP TABLE IF EXISTS `price_rule`;
CREATE TABLE `price_rule`  (
  `city_code` char(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `vehicle_type` char(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `start_fare` double(4, 2) NULL DEFAULT NULL,
  `start_mile` int NULL DEFAULT NULL,
  `unit_price_per_mile` double(4, 2) NULL DEFAULT NULL,
  `unit_price_per_minute` double(4, 2) NULL DEFAULT NULL,
  PRIMARY KEY (`city_code`, `vehicle_type`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of price_rule
-- ----------------------------
INSERT INTO `price_rule` VALUES ('110000', '1', 10.00, 3, 2.00, 1.00);

SET FOREIGN_KEY_CHECKS = 1;
