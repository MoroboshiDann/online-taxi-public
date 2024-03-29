package org.moroboshidan.controller;

import org.moroboshidan.internalcommon.dto.ResponseResult;
import org.moroboshidan.internalcommon.request.VerificationCodeDTO;
import org.moroboshidan.service.VerificationCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VerificationController {
    @Autowired
    private VerificationCodeService verificationCodeService;
    @GetMapping("/verification-code")
    public ResponseResult verificationCode(@RequestBody VerificationCodeDTO verificationCodeDTO) {
        String passengerPhone = verificationCodeDTO.getPassengerPhone();
        System.out.println("the passenger's phone number is: " + passengerPhone);
        // 调用服务，生成验证码
        return verificationCodeService.generateCode(passengerPhone);
    }

    @PostMapping("/verification-code-check")
    public ResponseResult verificationCodeCheck(@RequestBody VerificationCodeDTO verificationCodeDTO) {
        String passengerPhone = verificationCodeDTO.getPassengerPhone();
        String verificationCode = verificationCodeDTO.getVerificationCode();
        return verificationCodeService.checkVerificationCode(passengerPhone, verificationCode);
    }
}
