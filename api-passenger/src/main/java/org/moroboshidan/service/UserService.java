package org.moroboshidan.service;

import lombok.extern.slf4j.Slf4j;
import org.moroboshidan.internalcommon.dto.PassengerUser;
import org.moroboshidan.internalcommon.dto.ResponseResult;
import org.moroboshidan.internalcommon.dto.TokenResult;
import org.moroboshidan.internalcommon.request.VerificationCodeDTO;
import org.moroboshidan.internalcommon.util.JwtUtils;
import org.moroboshidan.remote.ServicePassengerUserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService {
    @Autowired
    private ServicePassengerUserClient servicePassengerUserClient;
    public ResponseResult getUserByAccessToken(String accessToken) {
        log.info("accessToken: " + accessToken);
        // 解析token
        TokenResult tokenResult = JwtUtils.checkToken(accessToken);
        String passengerPhone = tokenResult.getPhone();
        log.info("passengerPhone: " + passengerPhone);
        // 调用远程服务查询用户信息
        ResponseResult userInDB = servicePassengerUserClient.getUser(passengerPhone);
        return ResponseResult.success(userInDB.getData());
    }
}
