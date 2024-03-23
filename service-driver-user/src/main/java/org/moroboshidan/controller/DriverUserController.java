package org.moroboshidan.controller;

import org.moroboshidan.internalcommon.dto.DriverUser;
import org.moroboshidan.internalcommon.dto.ResponseResult;
import org.moroboshidan.service.DriverUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class DriverUserController {
    @Autowired
    private DriverUserService driverUserService;
    @GetMapping("/test")
    public ResponseResult test() {
        return driverUserService.tetGetDriverUser();
    }

    /**
     * 新增司机信息
     * @param
     * @return
     * @throws
     *
     */
    @PostMapping("/user")
    public ResponseResult addUser(@RequestBody DriverUser driverUser) {
        System.out.println(driverUser.toString());
        return driverUserService.addDriverUser(driverUser);
    }

    /**
     * 修改司机信息
     * @param
     * @return
     * @throws
     *
     */
    @PutMapping("/user")
    public ResponseResult updateUser(@RequestBody DriverUser driverUser) {
        return driverUserService.updateDriverUser(driverUser);
    }

    @GetMapping("/user")
    public ResponseResult getDriverUser(@RequestBody DriverUser driverUser) {
        return driverUserService.getDriverUserByPhone(driverUser.getDriverPhone());
    }

}
