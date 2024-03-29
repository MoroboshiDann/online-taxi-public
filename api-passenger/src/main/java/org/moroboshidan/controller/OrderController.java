package org.moroboshidan.controller;

import org.moroboshidan.internalcommon.dto.ResponseResult;
import org.moroboshidan.internalcommon.request.OrderRequest;
import org.moroboshidan.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @PostMapping("/add")
    public ResponseResult add(@RequestBody OrderRequest orderRequest) {
        System.out.println("making order ...");
        System.out.println(orderRequest.toString());
        return orderService.addOrder(orderRequest);
    }
}
