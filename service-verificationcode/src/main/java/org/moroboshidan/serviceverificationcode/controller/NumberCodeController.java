package org.moroboshidan.serviceverificationcode.controller;

import org.moroboshidan.internalcommon.dto.ResponseResult;
import org.moroboshidan.internalcommon.response.NumberCodeResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NumberCodeController {

    @GetMapping("/numberCode/{size}")
    public ResponseResult numberCode(@PathVariable int size) {
        System.out.println("size: " + size);
        int numberCode = (int) ((Math.random() * 9 + 1) * (Math.pow(10, size - 1)));
        NumberCodeResponse numberCodeResponse = new NumberCodeResponse(numberCode);
        System.out.println("generate code: " + numberCode);
        return ResponseResult.success(numberCodeResponse);
    }
}
