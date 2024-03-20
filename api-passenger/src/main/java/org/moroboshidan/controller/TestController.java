package org.moroboshidan.controller;

import org.moroboshidan.internalcommon.dto.ResponseResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/test")
    public String test() {
        return "test api passenger";
    }

    @GetMapping("/authTestNoToken")
    public ResponseResult authTest() {
        return ResponseResult.success("auth test without token");
    }
    @GetMapping("/authTest")
    public ResponseResult authTestWithToken() {
        return ResponseResult.success("auth test with token");
    }
}
