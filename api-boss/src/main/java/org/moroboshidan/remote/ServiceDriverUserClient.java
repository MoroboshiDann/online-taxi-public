package org.moroboshidan.remote;

import org.moroboshidan.internalcommon.dto.Car;
import org.moroboshidan.internalcommon.dto.DriverCarBindingRelationship;
import org.moroboshidan.internalcommon.dto.DriverUser;
import org.moroboshidan.internalcommon.dto.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("service-driver-user")
public interface ServiceDriverUserClient {
    @RequestMapping(method = RequestMethod.POST, value = "/user")
    ResponseResult addDriverUser(@RequestBody DriverUser driverUser);

    @RequestMapping(method = RequestMethod.PUT, value = "/user")
    ResponseResult updateDriverUser(@RequestBody DriverUser driverUser);

    @RequestMapping(method = RequestMethod.POST, value = "/car")
    ResponseResult addCar(@RequestBody Car car);

    @RequestMapping(method = RequestMethod.POST, value = "/driver-car-binding-relationship/bind")
    ResponseResult bindDriverCarRelationship(@RequestBody DriverCarBindingRelationship driverCarBindingRelationship);

    @RequestMapping(method = RequestMethod.POST, value = "/driver-car-binding-relationship/unbind")
    ResponseResult unbindDriverCarRelationship(@RequestBody DriverCarBindingRelationship driverCarBindingRelationship);

}
