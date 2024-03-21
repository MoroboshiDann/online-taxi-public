package org.moroboshidan.service;

import lombok.extern.slf4j.Slf4j;
import org.moroboshidan.internalcommon.dto.PassengerUser;
import org.moroboshidan.internalcommon.dto.ResponseResult;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService {
    public ResponseResult getUserByAccessToken(String accessToken) {
        log.info("accessToken: " + accessToken);
        // 解析token

        // 调用远程服务查询用户信息
        PassengerUser passengerUser = new PassengerUser();
        passengerUser.setPassengerName("moro");
        passengerUser.setProfilePhoto("img");
        return ResponseResult.success(passengerUser);
    }
}
