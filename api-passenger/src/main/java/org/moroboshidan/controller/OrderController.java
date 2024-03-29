package org.moroboshidan.controller;

import lombok.extern.slf4j.Slf4j;
import org.moroboshidan.internalcommon.dto.ResponseResult;
import org.moroboshidan.internalcommon.request.OrderRequest;
import org.moroboshidan.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {
    @Autowired
    private OrderService orderService;
    @PostMapping("/add")
    public ResponseResult add(@RequestBody OrderRequest orderRequest) {
        System.out.println("making order ...");
        System.out.println(orderRequest.toString());
        return orderService.addOrder(orderRequest);
    }

    @PostMapping("/dispatchOrder/{orderId}")
    public ResponseResult dispatchOrder(@PathVariable Long orderId) {
        log.info("is dispatching order");
        return orderService.dispatchOrder(orderId);
    }
}
