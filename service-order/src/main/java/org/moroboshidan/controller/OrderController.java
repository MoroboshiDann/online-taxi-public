package org.moroboshidan.controller;

import org.moroboshidan.internalcommon.dto.ResponseResult;
import org.moroboshidan.internalcommon.request.OrderRequest;
import org.moroboshidan.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    // @PostMapping("/dispatchOrder/{orderId}")
    // public ResponseResult dispatchOrder(@PathVariable Long orderId) {
    //     return orderService.dispatchOrder(orderId);
    // }

    /**
     * @description: 司机出发接乘客
     * @param orderRequest
     * @return: org.moroboshidan.internalcommon.dto.ResponseResult
     * @author: MoroboshiDan
     * @time: 2024/3/30 14:58
     */
    @PostMapping("/to-pick-up-passenger")
    public ResponseResult toPickUpPassenger(@RequestBody OrderRequest orderRequest) {
        return orderService.toPickUpPassenger(orderRequest);
    }

    /**
     * @description: 司机到达上车地点
     * @param orderRequest
     * @return: org.moroboshidan.internalcommon.dto.ResponseResult
     * @author: MoroboshiDan
     * @time: 2024/3/30 14:58
     */
    @PostMapping("/driver-arrived-departure")
    public ResponseResult driverArrivedDeparture(@RequestBody OrderRequest orderRequest) {
        return orderService.driverArrivedDeparture(orderRequest);
    }

    /**
     * @description: 乘客上车
     * @param orderRequest
     * @return: org.moroboshidan.internalcommon.dto.ResponseResult
     * @author: MoroboshiDan
     * @time: 2024/3/30 14:58
     */
    @PostMapping("/pick-up-passenger")
    public ResponseResult pickUpPassenger(@RequestBody OrderRequest orderRequest) {
        return orderService.pickUpPassenger(orderRequest);
    }

    /**
     * @description: 乘客下车
     * @param orderRequest
     * @return: org.moroboshidan.internalcommon.dto.ResponseResult
     * @author: MoroboshiDan
     * @time: 2024/3/30 15:20
     */
    @PostMapping("/passenger-getoff")
    public ResponseResult passengerGetoff(@RequestBody OrderRequest orderRequest) {
        return orderService.passengerGetoff(orderRequest);
    }
}
