package org.moroboshidan.controller;

import org.moroboshidan.internalcommon.dto.DriverUser;
import org.moroboshidan.internalcommon.dto.ResponseResult;
import org.moroboshidan.service.DriverUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DriverUserController {
    @Autowired
    private DriverUserService driverUserService;
    @GetMapping("/test")
    public ResponseResult test() {
        return driverUserService.tetGetDriverUser();
    }

    @PostMapping("/user")
    public ResponseResult addUser(@RequestBody DriverUser driverUser) {
        System.out.println(driverUser.toString());
        return driverUserService.addDriverUser(driverUser);
    }
}
