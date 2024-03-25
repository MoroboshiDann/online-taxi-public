package org.moroboshidan.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.moroboshidan.internalcommon.constant.CommonStatusEnum;
import org.moroboshidan.internalcommon.constant.OrderConstants;
import org.moroboshidan.internalcommon.dto.OrderInfo;
import org.moroboshidan.internalcommon.dto.ResponseResult;
import org.moroboshidan.internalcommon.request.OrderRequest;
import org.moroboshidan.internalcommon.util.RedisUtils;
import org.moroboshidan.mapper.OrderMapper;
import org.moroboshidan.remote.ServicePriceClient;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Service
public class OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private ServicePriceClient servicePriceClient;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * @param orderRequest
     * @description: 新增订单
     * @return: org.moroboshidan.internalcommon.dto.ResponseResult
     * @author: MoroboshiDan
     * @time: 2024/3/25 10:26
     */
    public ResponseResult add(OrderRequest orderRequest) {
        // 判断计价规则是否为最新
        ResponseResult<Boolean> isNewest = servicePriceClient.isNewest(orderRequest.getFareType(), orderRequest.getFareVersion());
        if (!(isNewest.getData())) {
            return ResponseResult.fail(CommonStatusEnum.PRICE_RULE_CHANGED.getCode(), CommonStatusEnum.PRICE_RULE_CHANGED.getValue());
        }
        // 判断有正在进行的订单，不允许下单
        if (hasOrderInProcess(orderRequest.getPassengerId())) {
            return ResponseResult.fail(CommonStatusEnum.ORDER_IN_PROCESS.getCode(), CommonStatusEnum.ORDER_IN_PROCESS.getValue());
        }
        // 判断设备是否为黑名单设备
        if (isBlacklisted(orderRequest.getDeviceCode())) {
            return ResponseResult.fail(CommonStatusEnum.DEVICE_IS_BLACKLISTED.getCode(), CommonStatusEnum.DEVICE_IS_BLACKLISTED.getValue());
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
     * @description: 检查当前乘客id是否有对应的正在进行的订单
     * @param passengerId
     * @return: boolean
     * @author: MoroboshiDan
     * @time: 2024/3/25 20:24
     */
    private boolean hasOrderInProcess(Long passengerId) {
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
     * @description: 检查当前设备是否在黑名单中
     * @param deviceCode 设备码
     * @return: boolean
     * @author: MoroboshiDan
     * @time: 2024/3/25 20:24
     */
    private boolean isBlacklisted(String deviceCode) {
        String deviceCodeKey = RedisUtils.blacklistedDevicePrefix + deviceCode;
        // 检查redis
        if (Boolean.TRUE.equals(stringRedisTemplate.hasKey(deviceCodeKey))) {
            if (Integer.parseInt(Objects.requireNonNull(stringRedisTemplate.opsForValue().get(deviceCodeKey))) >= 2) {
                return false;
            }
            stringRedisTemplate.opsForValue().increment(deviceCodeKey);
        }
        // 每小时只能下单两次
        stringRedisTemplate.opsForValue().setIfAbsent(deviceCodeKey, "1", 1L, TimeUnit.HOURS);
        return true;
    }
}
