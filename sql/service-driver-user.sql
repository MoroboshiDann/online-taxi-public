/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80035
 Source Host           : localhost:3306
 Source Schema         : service-driver-user

 Target Server Type    : MySQL
 Target Server Version : 80035
 File Encoding         : 65001

 Date: 23/03/2024 16:44:33
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for car
-- ----------------------------
DROP TABLE IF EXISTS `car`;
CREATE TABLE `car`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `address` char(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '车辆所在城市',
  `vehicle_no` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '车牌号',
  `plate_color` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '车牌颜色，1蓝色，2黄色，3黑色，4白色，5绿色，9其他',
  `seats` int NULL DEFAULT NULL COMMENT '核定载客位',
  `brand` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '车辆品牌',
  `model` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '车辆型号',
  `vehicle_type` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '车辆类型',
  `owner_name` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '车主姓名',
  `vehicle_color` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '车辆颜色',
  `engine_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '发动机号',
  `vin` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '车辆VIN号，以行驶证为准',
  `certify_date_a` date NULL DEFAULT NULL COMMENT '车辆注册日期',
  `fuel_type` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '燃料类型',
  `engine_displace` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '发动机排量',
  `trans_agency` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '运输证发证机构',
  `trans_area` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '运输证发证地',
  `trans_date_start` date NULL DEFAULT NULL,
  `trans_date_end` date NOT NULL,
  `certify_date_b` date NULL DEFAULT NULL COMMENT '车辆初次登记日期',
  `fix_state` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '车辆检修状态，0未检修，1已检修，2未知',
  `next_fix_date` date NULL DEFAULT NULL COMMENT '下次检修日期',
  `check_state` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '年审状态：0未年审，1年审合格，2年审不合格',
  `fee_print_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '发票打印设备号',
  `gps_brand` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `gps_model` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `gps_install_date` date NULL DEFAULT NULL,
  `register_date` date NULL DEFAULT NULL COMMENT '报备日期',
  `commercial_type` int NULL DEFAULT NULL,
  `fare_type` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '运价类型编码',
  `state` tinyint(1) NULL DEFAULT NULL COMMENT '0有效，1无效',
  `gmt_create` datetime NULL DEFAULT NULL,
  `gmt_update` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`, `fix_state`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of car
-- ----------------------------
INSERT INTO `car` VALUES (1771448262407843841, '110000', '京N12345', '1', 2, '大众', '辉腾', '小型轿车', NULL, '1', 'asdfaf', NULL, '2022-01-05', '1', '3.0T', '北京海淀车管所', '北京海淀', '2022-01-06', '2022-01-09', '2022-01-06', '1', '2022-08-01', '0', 'asdfasdf', '卫星品牌', '卫星型号', '2022-01-01', '2022-02-02', 1, '稍后关联', 1, '2024-03-23 16:05:52', '2024-03-23 16:05:52');

-- ----------------------------
-- Table structure for driver_car_binding_relationship
-- ----------------------------
DROP TABLE IF EXISTS `driver_car_binding_relationship`;
CREATE TABLE `driver_car_binding_relationship`  (
  `id` bigint NOT NULL,
  `driver_id` bigint NULL DEFAULT NULL,
  `car_id` bigint NULL DEFAULT NULL,
  `bind_state` int NULL DEFAULT NULL,
  `binding_time` datetime NULL DEFAULT NULL,
  `un_binding_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of driver_car_binding_relationship
-- ----------------------------
INSERT INTO `driver_car_binding_relationship` VALUES (1771439081428709377, 1, 2, 2, '2024-03-23 15:29:23', '2024-03-23 15:58:38');
INSERT INTO `driver_car_binding_relationship` VALUES (1771450831205060609, 12, 23, 2, '2024-03-23 16:16:05', '2024-03-23 16:16:20');

-- ----------------------------
-- Table structure for driver_user
-- ----------------------------
DROP TABLE IF EXISTS `driver_user`;
CREATE TABLE `driver_user`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `address` char(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '司机注册地，行政区划代码',
  `driver_name` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '司机姓名',
  `driver_phone` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `driver_gender` tinyint NULL DEFAULT NULL COMMENT '0：未知，1：男，2：女',
  `driver_birthday` date NULL DEFAULT NULL,
  `driver_nation` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `driver_contact_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `license_id` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `get_driver_license_date` date NULL DEFAULT NULL COMMENT '初次领取驾驶证日期',
  `driver_license_on` date NULL DEFAULT NULL,
  `driver_license_off` date NULL DEFAULT NULL,
  `taxi_driver` tinyint NULL DEFAULT NULL COMMENT '是否是巡游出租车',
  `certificate_no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '网络预约出租车驾驶员资格证号',
  `network_car_issue_organization` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '资格证发证机构',
  `network_car_issue_date` date NULL DEFAULT NULL COMMENT '资格证发证日期',
  `get_network_car_proof_date` date NULL DEFAULT NULL COMMENT '初次领取资格证日期',
  `network_car_proof_on` date NULL DEFAULT NULL COMMENT '资格证有效期起始日期',
  `network_car_proof_off` date NULL DEFAULT NULL,
  `register_date` date NULL DEFAULT NULL COMMENT '报备日期',
  `commercial_type` tinyint NULL DEFAULT NULL COMMENT '服务类型：1，网络预约出租车，2，巡游出租车，3，私人小客车合乘',
  `contract_company` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '驾驶员合同签署公司',
  `contract_on` date NULL DEFAULT NULL,
  `contract_off` date NULL DEFAULT NULL,
  `state` tinyint NULL DEFAULT NULL COMMENT '状态，0：有效，1：失效',
  `gmt_create` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `gmt_update` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of driver_user
-- ----------------------------
INSERT INTO `driver_user` VALUES (1, 'add', '张三', '12812311231', 1, '2022-01-01', '01', 'contact address', '0001', '2022-01-01', '2022-01-01', '2222-01-01', 1, '0001', 'organization', '2022-02-02', '2022-02-02', '2022-02-02', '2022-02-02', '2022-02-02', 3, 'company', '2022-02-02', '2222-02-03', 0, '2024-03-22 17:12:09', '2024-03-22 17:12:09');

SET FOREIGN_KEY_CHECKS = 1;
