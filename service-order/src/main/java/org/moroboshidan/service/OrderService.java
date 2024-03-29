package org.moroboshidan.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.moroboshidan.internalcommon.constant.CommonStatusEnum;
import org.moroboshidan.internalcommon.constant.OrderConstants;
import org.moroboshidan.internalcommon.dto.OrderInfo;
import org.moroboshidan.internalcommon.dto.PriceRule;
import org.moroboshidan.internalcommon.dto.ResponseResult;
import org.moroboshidan.internalcommon.request.OrderRequest;
import org.moroboshidan.internalcommon.request.PriceRuleRequest;
import org.moroboshidan.internalcommon.response.OrderDriverResponse;
import org.moroboshidan.internalcommon.response.TerminalResponse;
import org.moroboshidan.internalcommon.util.RedisUtils;
import org.moroboshidan.mapper.OrderMapper;
import org.moroboshidan.remote.ServiceDriverUserClient;
import org.moroboshidan.remote.ServiceMapClient;
import org.moroboshidan.remote.ServicePriceClient;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private ServicePriceClient servicePriceClient;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private ServiceDriverUserClient serviceDriverUserClient;
    @Autowired
    private ServiceMapClient serviceMapClient;

    /**
     * @param orderRequest
     * @description: 新增订单
     * @return: org.moroboshidan.internalcommon.dto.ResponseResult
     * @author: MoroboshiDan
     * @time: 2024/3/25 10:26
     */
    public ResponseResult add(OrderRequest orderRequest) {
        log.info(orderRequest.toString());
        // 判断计价规则是否为最新
        ResponseResult<Boolean> isNewest = servicePriceClient.isNewest(new PriceRuleRequest(orderRequest.getFareType(), orderRequest.getFareVersion()));
        if (!(isNewest.getData())) {
            return ResponseResult.fail(CommonStatusEnum.PRICE_RULE_CHANGED.getCode(), CommonStatusEnum.PRICE_RULE_CHANGED.getValue());
        }
        // 判断有正在进行的订单，不允许下单
        if (hasOrderInProcessPassenger(orderRequest.getPassengerId())) {
            return ResponseResult.fail(CommonStatusEnum.ORDER_IN_PROCESS.getCode(), CommonStatusEnum.ORDER_IN_PROCESS.getValue());
        }
        // 判断该地区是否开通业务
        if (!isPriceRuleExists(orderRequest)) {
            return ResponseResult.fail(CommonStatusEnum.NOT_IN_SERVICE_REGION.getCode(), CommonStatusEnum.NOT_IN_SERVICE_REGION.getValue());
        }
        // 判断设备是否为黑名单设备
        if (isBlacklisted(orderRequest.getDeviceCode())) {
            return ResponseResult.fail(CommonStatusEnum.DEVICE_IS_BLACKLISTED.getCode(), CommonStatusEnum.DEVICE_IS_BLACKLISTED.getValue());
        }
        // 判断该地区是否有可用司机
        if (!hasAvailableDriver(orderRequest.getAddress())) {
            return ResponseResult.fail(CommonStatusEnum.NO_AVAILABLE_DRIVER.getCode(), CommonStatusEnum.NO_AVAILABLE_DRIVER.getValue());
        }
        System.out.println("making order");
        OrderInfo orderInfo = new OrderInfo();
        BeanUtils.copyProperties(orderRequest, orderInfo);
        orderInfo.setOrderStatus(OrderConstants.ORDER_START);
        LocalDateTime now = LocalDateTime.now();
        orderInfo.setGmtCreate(now);
        orderInfo.setGmtUpdate(now);
        orderMapper.insert(orderInfo);
        return ResponseResult.success();
    }

    /**
     * @param orderId
     * @description: 分发订单
     * @return: org.moroboshidan.internalcommon.dto.ResponseResult
     * @author: MoroboshiDan
     * @time: 2024/3/29 15:01
     */
    public ResponseResult dispatchOrder(Long orderId) {
        OrderInfo orderInfo = orderMapper.selectById(orderId);
        return dispatchRealTimeOrder(orderInfo);
    }

    /**
     * @param cityCode
     * @description: 根据城市编码查询当前城市是否有可用司机
     * @return: boolean
     * @author: MoroboshiDan
     * @time: 2024/3/26 18:54
     */
    private boolean hasAvailableDriver(String cityCode) {
        return serviceDriverUserClient.hasAvailableDrivers(cityCode).getData();
    }

    /**
     * @param passengerId
     * @description: 检查当前乘客id是否有对应的正在进行的订单
     * @return: boolean
     * @author: MoroboshiDan
     * @time: 2024/3/25 20:24
     */
    private boolean hasOrderInProcessPassenger(Long passengerId) {
        // 判断有正在进行的订单，不允许下单
        LambdaQueryWrapper<OrderInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(OrderInfo::getPassengerId, passengerId);
        queryWrapper.and(wrapper -> wrapper
                .eq(OrderInfo::getOrderStatus, OrderConstants.ORDER_START).or()
                .eq(OrderInfo::getOrderStatus, OrderConstants.DRIVER_ARRIVE_DEPARTURE).or()
                .eq(OrderInfo::getOrderStatus, OrderConstants.DRIVER_TO_PICK_UP_PASSENGER).or()
                .eq(OrderInfo::getOrderStatus, OrderConstants.DRIVER_ARRIVE_DEPARTURE).or()
                .eq(OrderInfo::getOrderStatus, OrderConstants.PICK_UP_PASSENGER).or()
                .eq(OrderInfo::getOrderStatus, OrderConstants.DRIVER_ARRIVE_DEPARTURE).or()
                .eq(OrderInfo::getOrderStatus, OrderConstants.PASSENGER_GETOFF).or()
                .eq(OrderInfo::getOrderStatus, OrderConstants.TO_START_PAY).or());
        int count = orderMapper.selectCount(queryWrapper);
        return count > 0;
    }

    /**
     * @param driverId
     * @description: 检查当前乘客id是否有对应的正在进行的订单
     * @return: boolean
     * @author: MoroboshiDan
     * @time: 2024/3/25 20:24
     */
    private boolean hasOrderInProcessDriver(Long driverId) {
        // 判断有正在进行的订单，不允许下单
        LambdaQueryWrapper<OrderInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(OrderInfo::getDriverId, driverId);
        queryWrapper.and(wrapper -> wrapper
                .eq(OrderInfo::getOrderStatus, OrderConstants.DRIVER_RECEIVE_ORDER).or()
                .eq(OrderInfo::getOrderStatus, OrderConstants.DRIVER_TO_PICK_UP_PASSENGER).or()
                .eq(OrderInfo::getOrderStatus, OrderConstants.DRIVER_ARRIVE_DEPARTURE).or()
                .eq(OrderInfo::getOrderStatus, OrderConstants.PICK_UP_PASSENGER).or()
                .eq(OrderInfo::getOrderStatus, OrderConstants.DRIVER_ARRIVE_DEPARTURE).or());
        int count = orderMapper.selectCount(queryWrapper);
        log.info("driver's orders in process: " + count);
        return count > 0;
    }

    /**
     * @param deviceCode 设备码
     * @description: 检查当前设备是否在黑名单中
     * @return: boolean
     * @author: MoroboshiDan
     * @time: 2024/3/25 20:24
     */
    private boolean isBlacklisted(String deviceCode) {
        String deviceCodeKey = RedisUtils.blacklistedDevicePrefix + deviceCode;
        // 检查redis
        if (Boolean.TRUE.equals(stringRedisTemplate.hasKey(deviceCodeKey))) {
            if (Integer.parseInt(Objects.requireNonNull(stringRedisTemplate.opsForValue().get(deviceCodeKey))) >= 2) {
                return true;
            }
            stringRedisTemplate.opsForValue().increment(deviceCodeKey);
        }
        // 每小时只能下单两次
        stringRedisTemplate.opsForValue().setIfAbsent(deviceCodeKey, "1", 1L, TimeUnit.HOURS);
        return false;
    }

    /**
     * @param orderRequest
     * @description: 检查计价规则是否存在
     * @return: boolean
     * @author: MoroboshiDan
     * @time: 2024/3/25 20:58
     */
    private boolean isPriceRuleExists(OrderRequest orderRequest) {
        String[] s = orderRequest.getFareType().split("\\$");
        PriceRule priceRule = new PriceRule();
        priceRule.setCityCode(s[0]);
        priceRule.setVehicleType(s[1]);
        ResponseResult<Boolean> priceRuleExists = servicePriceClient.isPriceRuleExists(priceRule);
        return priceRuleExists.getData();
    }

    private ResponseResult dispatchRealTimeOrder(OrderInfo orderInfo) {
        String depLongitude = orderInfo.getDepLongitude();
        String depLatitude = orderInfo.getDepLatitude();
        String center = depLatitude + "," + depLongitude;
        List<Integer> radiusList = new ArrayList<>();
        radiusList.add(2000);
        radiusList.add(4000);
        radiusList.add(5000);
        boolean flag = false;
        for (Integer radius : radiusList) {
            List<TerminalResponse> terminalResponseList = serviceMapClient.terminalAroundSearch(center, radius).getData();
            log.info("当前搜索半径为：" + radius + "m, 搜索结果为" + terminalResponseList.size());
            if (terminalResponseList.isEmpty()) {
                continue;
            }
            // 解析结果，根据返回的tid 和 carId查询车辆信息
            for (TerminalResponse terminalResponse : terminalResponseList) {
                Long carId = terminalResponse.getCarId();
                log.info("当前判断id为:" + carId + "的车辆");
                // 找到carId对应的车辆信息，并且该车辆是出车状态
                ResponseResult<OrderDriverResponse> result = serviceDriverUserClient.getAvailableDriver(carId);
                if (result.getCode() == CommonStatusEnum.NO_AVAILABLE_DRIVER.getCode()) {
                    continue;
                }
                OrderDriverResponse availableDriver = result.getData();
                Long driverId = availableDriver.getDriverId();
                // 找到该车辆对应的司机，然后确定该司机没有正在进行的订单
                synchronized ((driverId + "").intern()) { // 将字符串存入常量池，保证线程获取的都是同一个string对象
                    if (hasOrderInProcessDriver(driverId)) {
                        continue;
                    }
                    log.info("找到了当前没有订单的、正在出车的司机");
                    // 订单匹配司机
                    orderInfo.setCarId(carId);
                    orderInfo.setDriverId(driverId);
                    orderInfo.setDirverPhone(availableDriver.getDriverPhone());
                    // 查询当前车辆信息和司机信息
                    // 司机和车辆信息，从service-driver-user中获取
                    orderInfo.setVehicleNo(availableDriver.getVehicleNo());
                    orderInfo.setLicenseId(availableDriver.getLicenseId());
                    orderInfo.setOrderStatus(OrderConstants.DRIVER_RECEIVE_ORDER);
                    orderInfo.setReceiveOrderTime(LocalDateTime.now());
                    // 以下信息从service-map中获取
                    orderInfo.setReceiveOrderCarLongitude(terminalResponse.getLongitude());
                    orderInfo.setReceiveOrderCarLatitude(terminalResponse.getLatitude());
                    orderMapper.updateById(orderInfo);
                    flag = true;
                    break;
                }
            }
            if (flag) {
                break;
            }
        }
        if (flag) {
            log.info("找到车辆");
        } else {
            log.info("没找到车辆");
        }
        return ResponseResult.success();
    }

}
