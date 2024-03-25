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

 Date: 25/03/2024 21:06:51
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
  `fare_type` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `fare_version` int NOT NULL,
  PRIMARY KEY (`city_code`, `vehicle_type`, `fare_version`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of price_rule
-- ----------------------------
INSERT INTO `price_rule` VALUES ('110000', '1', 15.00, 3, 3.20, 1.00, '110000$1', 1);
INSERT INTO `price_rule` VALUES ('110000', '1', 12.00, 2, 3.20, 2.00, '110000$1', 2);
INSERT INTO `price_rule` VALUES ('130000', '2', 10.00, 2, 2.00, 0.50, '130000$2', 1);

SET FOREIGN_KEY_CHECKS = 1;
