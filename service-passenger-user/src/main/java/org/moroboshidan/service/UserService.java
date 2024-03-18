package org.moroboshidan.service;

import org.moroboshidan.internalcommon.dto.ResponseResult;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public ResponseResult loginOfRegistry(String passengerPhone) {
        System.out.println("user service");
        // 根据手机号查询用户信息

        // 判断用户信息是否存在

        return ResponseResult.success();
    }
}
