/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80035
 Source Host           : localhost:3306
 Source Schema         : service-order

 Target Server Type    : MySQL
 Target Server Version : 80035
 File Encoding         : 65001

 Date: 25/03/2024 21:07:03
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for order_info
-- ----------------------------
DROP TABLE IF EXISTS `order_info`;
CREATE TABLE `order_info`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `passenger_id` bigint NULL DEFAULT NULL,
  `passenger_phone` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `driver_id` bigint NULL DEFAULT NULL,
  `dirver_phone` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `car_id` bigint NULL DEFAULT NULL,
  `address` char(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '订单所在地',
  `order_time` datetime NULL DEFAULT NULL COMMENT '订单发起时间',
  `depart_time` datetime NULL DEFAULT NULL COMMENT '预计用车时间',
  `departure` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '出发地具体地址',
  `dep_longitude` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '出发地经纬度',
  `dep_latitude` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `destination` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '目的地具体地址',
  `dest_longitude` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '目的地经纬度',
  `dest_latitude` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `encrypt` int NULL DEFAULT NULL COMMENT '加密方式',
  `fare_type` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '收费类型',
  `fare_version` int NULL DEFAULT NULL,
  `receive_order_car_longitude` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '接到订单时，司机初始位置经纬度',
  `receive_order_car_latitude` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `receive_order_time` datetime NULL DEFAULT NULL,
  `license_id` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '驾照编号',
  `vehicle_no` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '车牌号',
  `to_pick_up_passenger_time` datetime NULL DEFAULT NULL COMMENT '司机出发去接乘客的时间',
  `to_pick_up_passenger_longitude` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '位置',
  `to_pick_up_passenger_latitude` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `to_pick_up_passenger_address` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '出发去接乘客时，司机的地点',
  `drver_addrived_departure_time` datetime NULL DEFAULT NULL COMMENT '司机到达上车地点的时间',
  `pick_up_passenger_time` datetime NULL DEFAULT NULL COMMENT '乘客上车时间',
  `pick_up_passenger_longitude` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `pick_up_passenger_latitude` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `passenger_getoff_time` datetime NULL DEFAULT NULL,
  `passenger_getoff_longitude` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `passenger_getoff_latitude` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `cancel_time` datetime NULL DEFAULT NULL,
  `cancel_operator` int NULL DEFAULT NULL COMMENT '订单撤销发起者',
  `cancel_type_code` int NULL DEFAULT NULL COMMENT '撤销类型代码：1乘客提前撤销，2驾驶员提前撤销，3平台公司撤销，4乘客违约撤销，5驾驶员违约撤销',
  `drive_mile` bigint NULL DEFAULT NULL,
  `drive_time` bigint NULL DEFAULT NULL,
  `order_status` int NULL DEFAULT NULL COMMENT '订单状态：1订单开始，2司机接单，3去接乘客，4司机到达上车点，5乘客上车，开始行程，6达到目的地，7发起收款，8支付完成，9订单取消',
  `gmt_create` datetime NULL DEFAULT NULL,
  `gmt_update` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order_info
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
