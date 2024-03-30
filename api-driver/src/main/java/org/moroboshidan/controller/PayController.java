package org.moroboshidan.controller;

import org.moroboshidan.internalcommon.dto.ResponseResult;
import org.moroboshidan.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pay")
public class PayController {
    @Autowired
    private PayService payService;

    @PostMapping("/push-pay-info")
    public ResponseResult pushPayInfo(@RequestParam String orderId, @RequestParam String price, @RequestParam Long passengerId) {
        return payService.pushPayInfo(orderId, price, passengerId);
    }
}
