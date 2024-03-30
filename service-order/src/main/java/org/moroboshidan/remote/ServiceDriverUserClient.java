package org.moroboshidan.remote;

import org.moroboshidan.internalcommon.dto.Car;
import org.moroboshidan.internalcommon.dto.ResponseResult;
import org.moroboshidan.internalcommon.response.OrderDriverResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient("service-driver-user")
public interface ServiceDriverUserClient {
    @RequestMapping(method = RequestMethod.GET, value = "has-available-drivers")
    ResponseResult<Boolean> hasAvailableDrivers(@RequestParam String cityCode);

    @RequestMapping(method = RequestMethod.GET, value = "/car")
    ResponseResult<Car> getCarById(@RequestParam Long carId);

    @RequestMapping(method = RequestMethod.GET, value = "/get-available-driver/{carId}")
    ResponseResult<OrderDriverResponse> getAvailableDriver(@PathVariable Long carId);

    @GetMapping("/car")
    ResponseResult<Car> getCarInfoById(Long carId);
}
