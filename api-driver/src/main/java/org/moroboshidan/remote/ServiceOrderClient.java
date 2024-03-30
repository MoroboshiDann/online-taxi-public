package org.moroboshidan.remote;

import org.moroboshidan.internalcommon.dto.ResponseResult;
import org.moroboshidan.internalcommon.request.OrderRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("service-order")
public interface ServiceOrderClient {
    @PostMapping("/to-pick-up-passenger")
    ResponseResult toPickUpPassenger(@RequestBody OrderRequest orderRequest);

    @PostMapping("/driver-arrived-departure")
    ResponseResult driverArrivedDeparture(@RequestBody OrderRequest orderRequest);

    @PostMapping("/pick-up-passenger")
    ResponseResult pickUpPassenger(@RequestBody OrderRequest orderRequest);

    @PostMapping("/passenger-getoff")
    ResponseResult passengerGetoff(@RequestBody OrderRequest orderRequest);
}
