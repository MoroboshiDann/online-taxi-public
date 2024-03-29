package org.moroboshidan.controller;

import org.moroboshidan.internalcommon.constant.HeaderParamConstants;
import org.moroboshidan.internalcommon.dto.ResponseResult;
import org.moroboshidan.internalcommon.request.OrderRequest;
import org.moroboshidan.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    /**
     * @description: 用户下订单，本服务新增订单
     * @param orderRequest
     * @return: org.moroboshidan.internalcommon.dto.ResponseResult
     * @author: MoroboshiDan
     * @time: 2024/3/25 10:25
     */
    @PostMapping("/add")
    public ResponseResult add(@RequestBody OrderRequest orderRequest) {
        return orderService.add(orderRequest);
    }
}
