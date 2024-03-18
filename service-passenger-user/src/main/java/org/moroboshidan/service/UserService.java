package org.moroboshidan.service;

import org.moroboshidan.dto.PassengerUser;
import org.moroboshidan.internalcommon.dto.ResponseResult;
import org.moroboshidan.mapper.PassengerUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {
    @Autowired
    private PassengerUserMapper passengerUserMapper;
    public ResponseResult loginOfRegistry(String passengerPhone) {
        System.out.println("user service");
        // 根据手机号查询用户信息
        Map<String, Object> map = new HashMap<>();
        map.put("passenger_phone", passengerPhone);
        List<PassengerUser> passengerUsers = passengerUserMapper.selectByMap(map);
        // 判断用户信息是否存在
        if (passengerUsers.size() == 0) {
            PassengerUser passengerUser = new PassengerUser();
            passengerUser.setPassengerName("张三");
            passengerUser.setPassengerGender((byte) 0);
            passengerUser.setState((byte) 0);
            passengerUser.setPassengerPhone(passengerPhone);
            passengerUser.setGmtCreate(LocalDateTime.now());
            passengerUser.setGmtModified(LocalDateTime.now());
            passengerUserMapper.insert(passengerUser);
        }
        return ResponseResult.success();
    }
}