package org.moroboshidan.service;

import org.moroboshidan.internalcommon.dto.OrderInfo;
import org.moroboshidan.internalcommon.dto.ResponseResult;
import org.moroboshidan.internalcommon.request.OrderRequest;
import org.moroboshidan.mapper.OrderMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    private OrderMapper orderMapper;

    /**
     * @description: 新增订单
     * @param orderRequest
     * @return: org.moroboshidan.internalcommon.dto.ResponseResult
     * @author: MoroboshiDan
     * @time: 2024/3/25 10:26
     */
    public ResponseResult add(OrderRequest orderRequest) {
        System.out.println("making order");
        OrderInfo orderInfo = new OrderInfo();
        BeanUtils.copyProperties(orderRequest, orderInfo);
        orderMapper.insert(orderInfo);
        return ResponseResult.success();
    }

    public void test() {
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setAddress("110000");
        orderMapper.insert(orderInfo);
    }
}
