package org.moroboshidan.controller;

import org.moroboshidan.internalcommon.dto.DriverUser;
import org.moroboshidan.internalcommon.dto.DriverUserWorkStatus;
import org.moroboshidan.internalcommon.dto.ResponseResult;
import org.moroboshidan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PutMapping("/user")
    public ResponseResult updateUser(@RequestBody DriverUser driverUser) {
        return  userService.updateUser(driverUser);
    }

    @PostMapping("/driver-user-work-status-ongoing")
    public ResponseResult changeWorkStatusOnGoing(@RequestBody DriverUserWorkStatus driverUserWorkStatus) {
        return userService.changWorkStatusOnGoing(driverUserWorkStatus);
    }

    @PostMapping("/driver-user-work-status-offwork")
    public ResponseResult changeWorkStatusOffWork(@RequestBody DriverUserWorkStatus driverUserWorkStatus) {
        return userService.changWorkStatusOffWork(driverUserWorkStatus);
    }
}
