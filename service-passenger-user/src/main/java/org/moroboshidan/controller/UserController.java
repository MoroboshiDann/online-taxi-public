package org.moroboshidan.controller;

import org.moroboshidan.internalcommon.dto.ResponseResult;
import org.moroboshidan.internalcommon.request.VerificationCodeDTO;
import org.moroboshidan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/user")
    public ResponseResult loginOrRegistry(@RequestBody VerificationCodeDTO verificationCodeDTO) {
        String passengerPhone = verificationCodeDTO.getPassengerPhone();
        System.out.println("phone number: " + passengerPhone);
        return userService.loginOfRegistry(verificationCodeDTO);
    }

    @GetMapping("/user/{passengerPhone}")
    public  ResponseResult getUser(@PathVariable String passengerPhone) {
        return userService.getUserByPhone(passengerPhone);
    }

}
