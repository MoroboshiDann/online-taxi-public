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

 Date: 28/03/2024 19:31:27
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for car
-- ----------------------------
DROP TABLE IF EXISTS `car`;
CREATE TABLE `car`  (
  `trname` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `trid` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `tid` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
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
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1771853769371152385 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of car
-- ----------------------------
INSERT INTO `car` VALUES ('', '180', '864911259', 1771852239662346241, '110000', '京A1000', '1', 2, '奔驰', 'glc', '豪华轿车', NULL, '1', 'asdfaf', NULL, '2022-01-05', '1', '3.0T', '北京海淀车管所', '北京海淀', '2022-01-06', '2022-01-09', '2022-01-06', '1', '2022-08-01', '0', 'asdfasdf', '卫星品牌', '卫星型号', '2022-01-01', '2022-02-02', 1, '稍后关联', 1, '2024-03-24 18:51:08', '2024-03-24 18:51:08');
INSERT INTO `car` VALUES ('', '200', '864921501', 1771853769371152385, '110000', '京A0000', '1', 2, '劳斯莱斯', '库里南', '豪华轿车', NULL, '1', 'asdfaf', NULL, '2022-01-05', '1', '3.0T', '北京海淀车管所', '北京海淀', '2022-01-06', '2022-01-09', '2022-01-06', '1', '2022-08-01', '0', 'asdfasdf', '卫星品牌', '卫星型号', '2022-01-01', '2022-02-02', 1, '稍后关联', 1, '2024-03-24 18:57:13', '2024-03-24 18:57:13');
INSERT INTO `car` VALUES ('', '220', '867392410', 1773249969211158530, '420100', '鄂A0000', '1', 2, 'BYD', '宋', '电车', NULL, '1', 'asdfaf', NULL, '2022-01-05', '1', '3.0T', '武汉洪山车管所', '武汉洪山', '2022-01-06', '2022-01-09', '2022-01-06', '1', '2022-08-01', '0', 'asdfasdf', '卫星品牌', '卫星型号', '2022-01-01', '2022-02-02', 1, '420100$1', 1, '2024-03-28 15:25:13', '2024-03-28 15:25:13');

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
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of driver_car_binding_relationship
-- ----------------------------
INSERT INTO `driver_car_binding_relationship` VALUES (1773250508741259266, 1773248862904758273, 1773249969211158530, 1, '2024-03-28 15:27:21', NULL);

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
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of driver_user
-- ----------------------------
INSERT INTO `driver_user` VALUES (1, '110000', '张三', '12812311231', 1, '2022-01-01', '01', 'contact address', '0001', '2022-01-01', '2022-01-01', '2222-01-01', 1, '0001', 'organization', '2022-02-02', '2022-02-02', '2022-02-02', '2022-02-02', '2022-02-02', 3, 'company', '2022-02-02', '2222-02-03', 0, '2024-03-26 18:39:27', '2024-03-26 18:39:27');
INSERT INTO `driver_user` VALUES (1773248862904758273, '420100', '华子', '13812311231', 1, '2022-01-01', '01', '光谷世界城', 'c100001', '2022-01-01', '2022-01-01', '2222-01-01', 1, '0001', 'organization', '2022-02-02', '2022-02-02', '2022-02-02', '2022-02-02', '2022-02-03', 3, 'company', '2022-02-02', '2222-02-03', 0, '2024-03-28 15:20:49', '2024-03-28 15:20:49');

-- ----------------------------
-- Table structure for driver_user_work_status
-- ----------------------------
DROP TABLE IF EXISTS `driver_user_work_status`;
CREATE TABLE `driver_user_work_status`  (
  `id` bigint NOT NULL,
  `driver_id` bigint NULL DEFAULT NULL,
  `work_status` int NULL DEFAULT NULL COMMENT '0收车，1出车，2暂停',
  `gmt_create` datetime NULL DEFAULT NULL,
  `gmt_modified` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of driver_user_work_status
-- ----------------------------
INSERT INTO `driver_user_work_status` VALUES (1, 1, 0, NULL, '2024-03-23 20:55:38');
INSERT INTO `driver_user_work_status` VALUES (1773248862971867137, 1773248862904758273, 1, NULL, '2024-03-28 15:45:05');

-- ----------------------------
-- View structure for v_city_driver_user_work_status
-- ----------------------------
DROP VIEW IF EXISTS `v_city_driver_user_work_status`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `v_city_driver_user_work_status` AS select `t1`.`id` AS `driver_id`,`t1`.`address` AS `city_code`,`t2`.`work_status` AS `work_status` from (`driver_user` `t1` left join `driver_user_work_status` `t2` on((`t1`.`id` = `t2`.`driver_id`)));

SET FOREIGN_KEY_CHECKS = 1;
