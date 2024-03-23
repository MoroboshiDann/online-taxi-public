package org.moroboshidan.controller;

import org.moroboshidan.internalcommon.dto.ResponseResult;
import org.moroboshidan.internalcommon.request.VerificationCodeDTO;
import org.moroboshidan.service.VerificationCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VerificationCodeController {
    @Autowired
    private VerificationCodeService verificationCodeService;
    @GetMapping("/verification-code")
    public ResponseResult getVerificationCode(@RequestBody VerificationCodeDTO verificationCodeDTO) {
        return verificationCodeService.checkAndSendVerificationCode(verificationCodeDTO);
    }
}
